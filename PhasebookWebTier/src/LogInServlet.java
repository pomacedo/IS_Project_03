

import java.io.IOException;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import client.artefact.PhasebookMainWS;
import client.artefact.PhasebookMainWSService;
import client.artefact.ClientInfo;

import eai.ManageBeanRemote;

/**
 * Servlet implementation class LogInServlet
 */
public class LogInServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LogInServlet() {
        super();
        // TODO Auto-generated constructor stub
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
		String email=request.getParameter("email");
		String password=request.getParameter("pass");
		PhasebookMainWSService mainWS = new PhasebookMainWSService();
		PhasebookMainWS ws = mainWS.getPhasebookMainWSPort();		
		HttpSession session = request.getSession();
		
		 client.artefact.ClientInfo resp = ws.checkLogIn(email,password);
		 System.out.println("WEB TIER DATA: "+resp.getEmail()+" "+resp.getId()+" "+resp.getName()+" photo "+resp.getIdPhoto());
		if(resp==null){
			session.setAttribute("error", "Login Failed");
			response.sendRedirect("index.jsp");
		}else{
			try {
				InitialContext ctx = new InitialContext();
				
				ManageBeanRemote personal = (ManageBeanRemote)ctx.lookup("ManageBean/remote");
				personal.setIdPhoto(resp.getIdPhoto());
				personal.setName(resp.getName());
				personal.setEmail(resp.getEmail());
				personal.setPassword(resp.getPassword());
				personal.setId(resp.getId());
				
				
				session.setAttribute("user", personal);
				response.sendRedirect("primary.jsp");
			} catch (NamingException e) {
				session.setAttribute("error", "Login Failed");
				response.sendRedirect("index.jsp");
			}
		}
		
	}

}
