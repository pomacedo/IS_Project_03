package example;
import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map; 
import java.util.Scanner;

import javax.ejb.EJB;
import javax.jws.*;

import data.Client;
import eai.*;

@WebService(name = "clientws", targetNamespace = "http://PhasebookWSs/clientWS")
public class clientWS {
	@EJB(mappedName = "ClientSessionBean/remote")
	ClientSessionBeanRemote user;
		 
	@WebMethod	
	public ClientInfo checkLogIn(@WebParam(name = "email") String email, @WebParam(name = "password") String password)
	{
		System.out.println("WEB SERVICE : CHECK LOG IN");
		ClientInfo c =user.checkLogIn(email,password);
		
		return c;
	}
	@WebMethod	
	public int createUser(@WebParam(name = "name") String name,@WebParam(name = "password") String password,@WebParam(name = "gender") String gender,@WebParam(name = "email") String email)
	{

		System.out.println("WebService : "+name+" "+password+"GENDER="+gender+"=");
		char g = gender.charAt(0);
		return user.addClient(name,password,g,email);		
	}
	
	@WebMethod	
	public List<Client> getSearch(@WebParam(name = "searchFor") String searchFor)
	{
		System.out.println("WEBSERVICE GETSEARCH!!!!!!!!!!!!!#"+searchFor+"#!!!!!!!!");
		if((searchFor == null) || searchFor.equals("."))
			searchFor="";
		System.out.println("WEBSERVICE GETSEARCH!!!!!!!!!!!!!#"+searchFor+"#!!!!!!!!");
		return user.getSearch(searchFor);		
	}
	
	@WebMethod	
	public Client getClientInfo(@WebParam(name = "id") int id)
	{
		return user.getClientInfo(id);		
	}
	
	@WebMethod	
	public boolean editProfile(@WebParam(name = "id") int id, @WebParam(name = "password")String password,@WebParam(name = "name") String name,@WebParam(name = "email")String email,@WebParam(name = "newPassword")String newPassword,@WebParam(name = "gender") String gender,@WebParam(name = "money") float money)
	{
			
		//System.out.println("WEB SERV EDIT "+user.editProfile(id, password, name, email, newPassword, gender, money));
		return user.editProfile(id,password,name,email,newPassword,gender.charAt(0), money);		
	}
	
	@WebMethod	
	public List<Client> getFriendsInfo(@WebParam(name = "ids") List<Integer> ids)
	{
		return user.getVariousClientInfo(ids);		
	}
	
}
