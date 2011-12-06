
import java.util.Iterator;
import java.util.List;
import java.io.File;
import java.io.IOException;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import client.artefact.PhasebookMainWS;
import client.artefact.PhasebookMainWSService;



import eai.ClientSessionBeanRemote;
import eai.ManageBeanRemote;
import eai.MessageBeanRemote;

/**
 * Servlet implementation class MessageServlet
 */
public class PostServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private static final String TMP_DIR_PATH = "c:\\tmp";
	private File tmpDir;
	private static final String DESTINATION_DIR_PATH ="../../../../deploy/ROOT.war/phasebookphotos";
	private File destinationDir;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PostServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    public void init(ServletConfig config) throws ServletException {
		super.init(config);
		tmpDir = new File(TMP_DIR_PATH);
		if(!tmpDir.isDirectory()) {
			throw new ServletException(TMP_DIR_PATH + " is not a directory");
		}
		String realPath = getServletContext().getRealPath(DESTINATION_DIR_PATH);
		System.out.println("A1"+realPath);
		destinationDir = new File(realPath);
		System.out.println("A2"+destinationDir.getAbsolutePath());
		if(!destinationDir.isDirectory()) {
			throw new ServletException(DESTINATION_DIR_PATH+" is not a directory");
		}
 
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		boolean fileSaved=false;
		String filePath="";
		ManageBeanRemote personal=(ManageBeanRemote)request.getSession().getAttribute("user");
		
		//PrintWriter out = response.getWriter(); 
	    response.setContentType("text/plain");
//	    out.println("<h1>Servlet File Upload Example using Commons File Upload</h1>");
//	    out.println();
 
		DiskFileItemFactory  fileItemFactory = new DiskFileItemFactory ();
		/*
		 *Set the size threshold, above which content will be stored on disk.
		 */
		fileItemFactory.setSizeThreshold(1*1024*1024); //1 MB
		/*
		 * Set the temporary directory to store the uploaded files of size above threshold.
		 */
		fileItemFactory.setRepository(tmpDir);
		
		
		String message="";
		int id_to=0;
		boolean isPrivate=true;
 
		ServletFileUpload uploadHandler = new ServletFileUpload(fileItemFactory);
		try {
			/*
			 * Parse the request
			 */
			List items = uploadHandler.parseRequest(request);
			Iterator itr = items.iterator();
			while(itr.hasNext()) {
				FileItem item = (FileItem) itr.next();
				/*
				 * Handle Form Fields.
				 */
				
				if(item.isFormField()) {
					if(item.getFieldName().equals("message"))
						message=item.getString();
					if(item.getFieldName().equals("to"))
						id_to=Integer.parseInt(item.getString());
					if(item.getFieldName().equals("private"))
						isPrivate=item.getString().equals("T")?true:false;
//					out.println("File Name = "+item.getFieldName()+", Value = "+item.getString());
				} else {
					//Handle Uploaded files.
//					out.println("Field Name = "+item.getFieldName()+
//						", File Name = "+item.getName()+
//						", Content type = "+item.getContentType()+
//						", File Size = "+item.getSize());
					/*
					 * Write file to the ultimate location.
					 */
					if(item.getSize()!=0){
						String name=System.currentTimeMillis()+"_"+item.getName();
						File file = new File(destinationDir,name);
						item.write(file);
						fileSaved=true;
						filePath="http://localhost:8080/phasebookphotos/"+name;
					}
					
				}
//				out.close();
			}
		}catch(FileUploadException ex) {
			log("Error encountered while parsing the request",ex);
		} catch(Exception ex) {
			log("Error encountered while uploading file",ex);
		}
				
		//InitialContext ctx; 
		RequestDispatcher dispatcher;
//		try {
		//ctx = new InitialContext();
		//MessageBeanRemote cb=(MessageBeanRemote)ctx.lookup("MessageBean/remote");
		PhasebookMainWSService mainWS = new PhasebookMainWSService();
		PhasebookMainWS ws = mainWS.getPhasebookMainWSPort();
		HttpSession session = request.getSession();
		if(!fileSaved){
			if(ws.sendMessage(personal.getId(), personal.getPassword(), id_to, message, isPrivate, ""))
				dispatcher = request.getRequestDispatcher("/messages.jsp?id="+id_to);
			else{
				session.setAttribute("error", "There was an error sending your message, try again..");
//				request.setAttribute("error", "There was an error sending your message, try again..");
				dispatcher = request.getRequestDispatcher("/messages.jsp?id="+id_to);
			}
		}else{
			if(ws.sendMessage(personal.getId(), personal.getPassword(), id_to, message, isPrivate, filePath)){
				dispatcher = request.getRequestDispatcher("/messages.jsp?id="+id_to);
			}else{
//				request.setAttribute("error", "There was an error sending your message, try again..");
				session.setAttribute("error", "There was an error sending your message, try again..");
				dispatcher = request.getRequestDispatcher("/messages.jsp?id="+id_to);
			}
		}				
//		} catch (NamingException e1) {
//			request.setAttribute("error", "There was an error sending your message, try again..");
//			dispatcher = request.getRequestDispatcher("/messages.jsp?id="+id_to);
//			dispatcher.forward(request, response);
//		}	
		dispatcher.forward(request, response);
		
		
		
	}

}
