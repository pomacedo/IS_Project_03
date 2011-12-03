import java.io.IOException;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import client.artefact.*;

import eai.ManageBeanRemote;

/**
 * Servlet implementation class EditProfileServlet
 */
public class EditProfileServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditProfileServlet() {
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
		ManageBeanRemote personal=(ManageBeanRemote)request.getSession().getAttribute("user");
		HttpSession session = request.getSession();
		PhasebookMainWSService mainWS = new PhasebookMainWSService();
		PhasebookMainWS ws = mainWS.getPhasebookMainWSPort();
		String name=request.getParameter("name");
		String email=request.getParameter("email");
		String password=request.getParameter("pass");
		float money=Float.parseFloat(request.getParameter("money"));
		char gender=request.getParameter("gender").charAt(0);
		
		boolean res= ws.editProfile(personal.getId(), personal.getPassword(), name, email, password, gender, money);
		if(!res)
			session.setAttribute("error", "Failed to edit your profile");
		
		RequestDispatcher dispatcher=request.getRequestDispatcher("/profile.jsp");
		dispatcher.forward(request, response);
	}

}
