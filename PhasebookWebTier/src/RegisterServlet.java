

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import client.artefact.PhasebookMainWS;
import client.artefact.PhasebookMainWSService;

/**
 * Servlet implementation class RegisterServlet
 */
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegisterServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String name=request.getParameter("name");
		String password=request.getParameter("pass");
		String email=request.getParameter("email");
		char gender=request.getParameter("gender").charAt(0);
		System.out.println("VOU INVOCAR");
		PhasebookMainWSService mainWS = new PhasebookMainWSService();
		PhasebookMainWS ws = mainWS.getPhasebookMainWSPort();
		if(ws.addClient(name, password, gender, email)==1){
			System.out.println("SUCESSSS!");
			session.setAttribute("errorreg", "Register Sucess!");
			response.sendRedirect("index.jsp");
		}else{
			System.out.println("NO SUCESSSS!");
			session.setAttribute("errorreg", "Register Failed!");
			response.sendRedirect("index.jsp");
		}
	}

}
