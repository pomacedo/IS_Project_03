import java.io.IOException;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import eai.BetBeanRemote;
//import eai.ClientSessionBeanRemote;
import eai.ManageBeanRemote;

/**
 * Servlet implementation class MakeNewBetServlet
 */
public class MakeNewBetServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MakeNewBetServlet() {
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
		HttpSession session = request.getSession();
		ManageBeanRemote personal=(ManageBeanRemote)request.getSession().getAttribute("user");
		InitialContext ctx;
		try {
			ctx = new InitialContext();
			BetBeanRemote bb=(BetBeanRemote)ctx.lookup("BetBean/remote");
			int betId=Integer.parseInt(request.getParameter("betId"));						
			int number=Integer.parseInt(request.getParameter("number"));
			if(!bb.makeNewBet(personal.getId(), personal.getPassword(), betId,number))
				session.setAttribute("error", "Failed to make your bet, maybe you dont have enough money");
			RequestDispatcher dispatcher=request.getRequestDispatcher("/bets.jsp");
			dispatcher.forward(request, response);
		} catch (NamingException e) {
			session.setAttribute("error", "Failed to make your bet");
			RequestDispatcher dispatcher=request.getRequestDispatcher("/bets.jsp");
			dispatcher.forward(request, response);
		}
	}

}
