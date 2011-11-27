package example;
import java.util.HashMap;
import java.util.Map;
import javax.ejb.EJB;
import javax.jws.*;

//import eai.*;

@WebService(name = "test", targetNamespace = "http://PhasebookWSs/testeWS")
public class testeWS {
	
//	@EJB(mappedName = "ClientSessionBean/remote")
//	ClientSessionBeanRemote user;
	
	@WebMethod
	public String algoMais(@WebParam(name = "email") String email,@WebParam(name = "password") String password)
	{
//		ClientInfo res = user.checkLogIn(email,password);
//		System.out.println("FUI AO EJB="+res.getId());
		
		//return (String) res.getName();
		return "OLA";
	}
}
