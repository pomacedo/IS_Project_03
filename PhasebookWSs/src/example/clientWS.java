package example;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.jws.*;

import org.hibernate.tuple.component.PojoComponentTuplizer;

import eai.*;

@WebService(name = "clientws", targetNamespace = "http://PhasebookWSs/clientWS")
public class clientWS {
	
	@EJB(mappedName = "ClientSessionBean/remote")
	ClientSessionBeanRemote user;
	
	@WebMethod	
	public ClientInfo checkLogIn(@WebParam(name = "email") String email, @WebParam(name = "password") String password)
	{
		
		ClientInfo res = user.checkLogIn(email,password);		
		
		return res;
	}
	
	
}
