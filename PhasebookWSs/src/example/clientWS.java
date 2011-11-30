package example;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.jws.*;

import org.hibernate.tuple.component.PojoComponentTuplizer;

import data.Client;

import eai.*;

@WebService(name = "clientws", targetNamespace = "http://PhasebookWSs/clientWS")
public class clientWS {
	
	@EJB(mappedName = "ClientSessionBean/remote")
	ClientSessionBeanRemote user;
	 
	@WebMethod	
	public ClientInfo checkLogIn(@WebParam(name = "email") String email, @WebParam(name = "password") String password)
	{
		return user.checkLogIn(email,password);		
	}
	@WebMethod	
	public int createUser(@WebParam(name = "name") String name,@WebParam(name = "password") String password,@WebParam(name = "gender") char gender,@WebParam(name = "email") String email)
	{
		return user.addClient(name,password,gender,email);		
	}
	
	@WebMethod	
	public List<Client> getSearch(@WebParam(name = "searchfor") String searchfor)
	{
		return user.getSearch(searchfor);		
	}
	
	@WebMethod	
	public Client getClientInfo(@WebParam(name = "id") int id)
	{
		return user.getClientInfo(id);		
	}
	
	@WebMethod	
	public boolean editProfile(@WebParam(name = "id") int id, @WebParam(name = "password")String password,@WebParam(name = "name") String name,@WebParam(name = "email")String email,@WebParam(name = "newPassword")String newPassword,@WebParam(name = "gender") char gender,@WebParam(name = "money") float money)
	{
		return user.editProfile(id,password,name,email,newPassword,gender, money);		
	}
	
	
}
