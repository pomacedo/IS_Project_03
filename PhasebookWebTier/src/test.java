

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import java.util.HashMap;
//import client.artefact2.PhasebookMainWS;
//import client.artefact2.PhasebookMainWSService;
import eai.ClientInfo;

/**
 * Servlet implementation class test
 */
public class test extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public test() {
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
		
//		String login = request.getParameter("email");
//		String pass = request.getParameter("password");
//		PhasebookMainWSService mainWS = new PhasebookMainWSService();
//		PhasebookMainWS ws = mainWS.getPhasebookMainWSPort();
//		
//		
//		 client.artefact2.ClientInfo resp = ws.test(login,pass);
//		
//		response.sendRedirect("index.jsp?resp="+resp.getName());
	}

}
