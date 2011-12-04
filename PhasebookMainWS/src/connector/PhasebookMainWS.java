package connector;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.jws.WebMethod;
import javax.jws.WebService;

import org.jboss.soa.esb.client.ServiceInvoker;
import org.jboss.soa.esb.couriers.FaultMessageException;
import org.jboss.soa.esb.listeners.message.MessageDeliverException;
import org.jboss.soa.esb.message.Body;
import org.jboss.soa.esb.message.Message;
import org.jboss.soa.esb.message.format.MessageFactory;
import org.jboss.soa.esb.services.registry.RegistryException;

import eai.ClientInfo;
import data.*;
@WebService
public class PhasebookMainWS {

	
	@WebMethod
	public ClientInfo checkLogIn(String email,String password)
	{
		System.setProperty("javax.xml.registry.ConnectionFactoryClass","org.apache.ws.scout.registry.ConnectionFactoryImpl");

		Message esbMessage = MessageFactory.getInstance().getMessage();
		
		HashMap requestMap = new HashMap();
		requestMap.put("email",email);
		requestMap.put("password",password);
		esbMessage.getBody().add(requestMap);
		
		Message retMessage = null;

		ServiceInvoker si;
		try {
			
			si = new ServiceInvoker("Client", "checkLogIn");
			retMessage = si.deliverSync(esbMessage, 10000L);
			
			ClientInfo ret = (ClientInfo) retMessage.getBody().get(Body.DEFAULT_LOCATION);
						 
			System.out.println("LOG ING ENDED");
			return ret;
		} catch (MessageDeliverException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FaultMessageException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (RegistryException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;		
		
	}
	
	@WebMethod
	public Client getClientInfo(int id)
	{
		System.setProperty("javax.xml.registry.ConnectionFactoryClass","org.apache.ws.scout.registry.ConnectionFactoryImpl");

		Message esbMessage = MessageFactory.getInstance().getMessage();
		
		HashMap requestMap = new HashMap();
		requestMap.put("id",id);
		
		esbMessage.getBody().add(requestMap);
		
		Message retMessage = null;

		ServiceInvoker si;
		try {
			
			si = new ServiceInvoker("Client", "getClientInfo");
			retMessage = si.deliverSync(esbMessage, 10000L);
			
			Client ret = (Client) retMessage.getBody().get(Body.DEFAULT_LOCATION);
						 
			System.out.println("getClientInfo ENDED "+ret.getId());
			return ret;
		} catch (MessageDeliverException e) {
			e.printStackTrace();
		} catch (FaultMessageException e) {
			e.printStackTrace();
		} catch (RegistryException e) {
			e.printStackTrace();
		}
		return null;		
		
	}
	
	
	@WebMethod
	public int addClient(String name,String password,String gender,String email){
		System.setProperty("javax.xml.registry.ConnectionFactoryClass","org.apache.ws.scout.registry.ConnectionFactoryImpl");

		Message esbMessage = MessageFactory.getInstance().getMessage();
//		System.out.println("TOUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUU");
//		System.out.println("VOU INVOCAR"+name+" | "+email+" | "+gender+" | "+password);
		HashMap requestMap = new HashMap();
		requestMap.put("name",name);
		requestMap.put("password",password);
		requestMap.put("gender",gender);
		requestMap.put("email",email);
		
		esbMessage.getBody().add(requestMap);
		
		Message retMessage = null;

		ServiceInvoker si;
		try {
			
			si = new ServiceInvoker("Client", "createUser");
			retMessage = si.deliverSync(esbMessage, 10000L);
			
			System.out.println("Pedido no WS enviado\n");
			
			Map responseMsg = (Map) retMessage.getBody().get(Body.DEFAULT_LOCATION);
			
			
			 Iterator i = responseMsg.keySet().iterator();
			 int response = Integer.parseInt((String) responseMsg.get(i.next()));
			 		 
			 
			 
			 System.out.println("\n ADD CLIENT response is: "+response);
			 
			
			return response;
		} catch (MessageDeliverException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FaultMessageException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (RegistryException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;		
		
	}
	
	@WebMethod
	public List<data.Message> getPost(int idViewer,String password, int idTo)
	{
		System.setProperty("javax.xml.registry.ConnectionFactoryClass","org.apache.ws.scout.registry.ConnectionFactoryImpl");

		Message esbMessage = MessageFactory.getInstance().getMessage();
		HashMap requestMap = new HashMap();
		requestMap.put("idViewer",idViewer);
		requestMap.put("password",password);
		requestMap.put("idTo",idTo);
		esbMessage.getBody().add(requestMap);
		System.out.println("MAIN WS GET POSTS "+idViewer+", "+password+", "+idTo);
		Message retMessage = null;

		ServiceInvoker si;
		try {
			
			si = new ServiceInvoker("Message", "getPosts");
			retMessage = si.deliverSync(esbMessage, 10000L);
			
			
			List<data.Message> ret = (List<data.Message>) retMessage.getBody().get(Body.DEFAULT_LOCATION);
			System.out.println("GET POSTS ENDED");
			return ret;
		}catch (MessageDeliverException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FaultMessageException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (RegistryException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
			
	@WebMethod
	public boolean sendMessage(int idFrom,String password,int idTo,String message,boolean isPrivate,String path){
		
		int idPhoto;
		if(path.equals(""))
		{
			idPhoto=-1;
		}
		else{
			idPhoto=addPhoto(path);
			if(idPhoto==-1)
				return false;
		}
		
		System.setProperty("javax.xml.registry.ConnectionFactoryClass","org.apache.ws.scout.registry.ConnectionFactoryImpl");
		Message esbMessage = MessageFactory.getInstance().getMessage();

		HashMap requestMap = new HashMap();
		requestMap.put("idFrom",idFrom);
		requestMap.put("password",password);
		requestMap.put("idTo",idTo);
		requestMap.put("message",message);
		requestMap.put("isPrivate",isPrivate);
		requestMap.put("idPhoto",idPhoto);
		
		esbMessage.getBody().add(requestMap);
		
		Message retMessage = null;

		ServiceInvoker si;
		try {
			
			si = new ServiceInvoker("Message", "sendMessage");
			retMessage = si.deliverSync(esbMessage, 10000L);
			
			System.out.println("Pedido no WS enviado\n");
			
			Map responseMsg = (Map) retMessage.getBody().get(Body.DEFAULT_LOCATION);
			System.out.println("\n ::::::::: "+responseMsg.toString());
			
			 Iterator i = responseMsg.keySet().iterator();
			 boolean response = Boolean.parseBoolean((String) responseMsg.get(i.next()));
			 
			 System.out.println("\nMessageSend response is: "+response);
			 
			 System.out.println("SEND MESSAGE DONE");
			return response;
		} catch (MessageDeliverException e) {
			e.printStackTrace();
		} catch (FaultMessageException e) {
			e.printStackTrace();
		} catch (RegistryException e) {
			e.printStackTrace();
		}
		return false;		
	}
	
	public int addPhoto(String path)
	{
		System.setProperty("javax.xml.registry.ConnectionFactoryClass","org.apache.ws.scout.registry.ConnectionFactoryImpl");
		Message esbMessage = MessageFactory.getInstance().getMessage();
		
//		System.out.println("VOU INVOCAR"+name+" | "+email+" | "+gender+" | "+password);
		HashMap requestMap = new HashMap();
		requestMap.put("path",path);		
		esbMessage.getBody().add(requestMap);
		
		Message retMessage = null;

		ServiceInvoker si;
		try {
			
			si = new ServiceInvoker("Photo", "addPhoto");
			retMessage = si.deliverSync(esbMessage, 10000L);
			
			System.out.println("Pedido no WS enviado\n");			
			int id = (Integer) retMessage.getBody().get(Body.DEFAULT_LOCATION);
			System.out.println("\n :::::::::id= "+id);			
			 
			return id;
		} catch (MessageDeliverException e) {
			e.printStackTrace();
		} catch (FaultMessageException e) {
			e.printStackTrace();
		} catch (RegistryException e) {
			e.printStackTrace();
		}
		return -1;
	}
	
	@WebMethod
	public boolean editProfile(int id,String password,String name,String email,String newPassword,char gender,float money){
		System.setProperty("javax.xml.registry.ConnectionFactoryClass","org.apache.ws.scout.registry.ConnectionFactoryImpl");

		Message esbMessage = MessageFactory.getInstance().getMessage();
//		System.out.println("TOUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUU");
//		System.out.println("VOU INVOCAR"+name+" | "+email+" | "+gender+" | "+password);
		HashMap requestMap = new HashMap();
		requestMap.put("id",id);
		requestMap.put("password",password);
		requestMap.put("name",name);
		requestMap.put("email",email);
		requestMap.put("newPassword",newPassword);
		requestMap.put("gender",""+gender);
		requestMap.put("money",money);
		
		
		esbMessage.getBody().add(requestMap);
		
		Message retMessage = null;

		ServiceInvoker si;
		try {
			
			si = new ServiceInvoker("Client", "editProfile");
			retMessage = si.deliverSync(esbMessage, 10000L);
			
			System.out.println("Pedido no WS enviado\n");
			
			boolean response = (Boolean) retMessage.getBody().get(Body.DEFAULT_LOCATION);
			
			 System.out.println("\n editProfile response is: "+response);
			 
			
			return response;
		} catch (MessageDeliverException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FaultMessageException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (RegistryException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;		
		
	}
	@WebMethod
	public List<Client> getFriends(int id){
		System.setProperty("javax.xml.registry.ConnectionFactoryClass","org.apache.ws.scout.registry.ConnectionFactoryImpl");

		Message esbMessage = MessageFactory.getInstance().getMessage();
		System.out.println("GETING FRIENDS ON MAIN WS---------------------");
//		System.out.println("VOU INVOCAR"+name+" | "+email+" | "+gender+" | "+password);
		HashMap requestMap = new HashMap();
		requestMap.put("id",id);
		esbMessage.getBody().add(requestMap);
		
		Message retMessage = null;

		ServiceInvoker si;
		try {
			
			si = new ServiceInvoker("Relation", "getRelations");
			retMessage = si.deliverSync(esbMessage, 10000L);
			
			System.out.println("MAIN WS - Pedido de GETRELATIONS enviado\n");
			List<Integer> response = (List<Integer>) retMessage.getBody().get(Body.DEFAULT_LOCATION);
			if(response.size()==0 || response==null)
				return null;
			System.out.println("\n --------------------GET_FRIENDS_RELATIONS response is: "+response.get(0));
			System.out.println("\n --------------------GET_FRIENDS_RELATIONS response is: "+response.size());
			HashMap requestMap2 = new HashMap();
			requestMap2.put("ids",response);
			esbMessage.getBody().add(requestMap2);
		
			si = new ServiceInvoker("Client", "getFriendsInfo");
			retMessage = si.deliverSync(esbMessage, 10000L);
			
			
			
			System.out.println("MAIN WS - Pedido de GETFRIENDS enviado\n");
			List<Client> response2 = (List<Client>) retMessage.getBody().get(Body.DEFAULT_LOCATION);
			
			System.out.println("\n ----------------------GETFRIENDSINFO response is: "+response2);
			 
			
			return response2;
		} catch (MessageDeliverException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FaultMessageException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (RegistryException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;		
		
	}
	
	@WebMethod
	public List<Relation> getNewRequests(int id, String password){
		System.setProperty("javax.xml.registry.ConnectionFactoryClass","org.apache.ws.scout.registry.ConnectionFactoryImpl");

		Message esbMessage = MessageFactory.getInstance().getMessage();
		System.out.println("getNewRequests ON MAIN WS");
//		System.out.println("VOU INVOCAR"+name+" | "+email+" | "+gender+" | "+password);
		HashMap requestMap = new HashMap();
		requestMap.put("id",id);
		requestMap.put("password",password);
		
		esbMessage.getBody().add(requestMap);
		
		Message retMessage = null;

		ServiceInvoker si;
		try {
			
			si = new ServiceInvoker("Relation", "getPendingRelations");
			retMessage = si.deliverSync(esbMessage, 10000L);
			
			System.out.println("MAIN WS - Pedido de getNewRequests enviado\n");
			List<Relation> response = (List<Relation>) retMessage.getBody().get(Body.DEFAULT_LOCATION);
						
			System.out.println("\n GET_FRIENDS_RELATIONS response is: "+response);
					
			return response;
		} catch (MessageDeliverException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FaultMessageException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (RegistryException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;		
		
	}
	
	@WebMethod
	public List<Client> getSearch(String searchFor){
		System.setProperty("javax.xml.registry.ConnectionFactoryClass","org.apache.ws.scout.registry.ConnectionFactoryImpl");

		Message esbMessage = MessageFactory.getInstance().getMessage();
		System.out.println("getSearch ON MAIN WS ###"+searchFor+"###");
		if(searchFor.equals(""))
			searchFor=".";
//		System.out.println("VOU INVOCAR"+name+" | "+email+" | "+gender+" | "+password);
		HashMap requestMap = new HashMap();
		requestMap.put("searchFor",searchFor);
		
		
		esbMessage.getBody().add(requestMap);
		
		Message retMessage = null;

		ServiceInvoker si;
		try {
			
			si = new ServiceInvoker("Client", "getSearch");
			retMessage = si.deliverSync(esbMessage, 10000L);
			
			System.out.println("MAIN WS - Pedido de getSearch enviado\n");
			List<Client> response = (List<Client>) retMessage.getBody().get(Body.DEFAULT_LOCATION);
			
			System.out.println("\n getSearch response is: "+response);
							 
			
			return response;
		} catch (MessageDeliverException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FaultMessageException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (RegistryException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;		
		
	}
	@WebMethod
	public boolean addFriend(int id, String password,int idTo){
		System.setProperty("javax.xml.registry.ConnectionFactoryClass","org.apache.ws.scout.registry.ConnectionFactoryImpl");

		Message esbMessage = MessageFactory.getInstance().getMessage();
		System.out.println("addFriend ON MAIN WS ###"+id+" "+password+" "+idTo+"###");

		HashMap requestMap = new HashMap();
		requestMap.put("id",id);
		requestMap.put("password",password);
		requestMap.put("idTo",idTo);
		esbMessage.getBody().add(requestMap);
		
		Message retMessage = null;

		ServiceInvoker si;
		try {
			si = new ServiceInvoker("Relation", "addFriend");
			retMessage = si.deliverSync(esbMessage, 10000L);
			System.out.println("MAIN WS - Pedido de addFriend enviado\n");
			boolean response = (Boolean) retMessage.getBody().get(Body.DEFAULT_LOCATION);
			System.out.println("\n addFriend response is: "+retMessage.getBody().get(Body.DEFAULT_LOCATION));
			return response;
			
		} catch (MessageDeliverException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FaultMessageException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (RegistryException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
//	@WebMethod
//	public void declineFriend(int id, String password,int idRel){
//		System.setProperty("javax.xml.registry.ConnectionFactoryClass","org.apache.ws.scout.registry.ConnectionFactoryImpl");
//
//		Message esbMessage = MessageFactory.getInstance().getMessage();
//		System.out.println("declineFriend ON MAIN WS ###"+id+" "+password+" "+idRel+"###");
//
//		HashMap requestMap = new HashMap();
//		requestMap.put("id",id);
//		requestMap.put("password",password);
//		requestMap.put("idRel",idRel);
//		esbMessage.getBody().add(requestMap);
//		
//		Message retMessage = null;
//
//		ServiceInvoker si;
//		try {
//			si = new ServiceInvoker("Relation", "declineFriend");
//			retMessage = si.deliverSync(esbMessage, 10000L);
//			System.out.println("MAIN WS - Pedido de declineFriend enviado\n");
//			
//			System.out.println("\n declineFriend response is: "+retMessage.getBody().get(Body.DEFAULT_LOCATION));
//							 
//			
//			
//		} catch (MessageDeliverException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (FaultMessageException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (RegistryException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//	}
	
	
//	@WebMethod
//	public void acceptFriend(int id, String password,int idRel){
//		System.setProperty("javax.xml.registry.ConnectionFactoryClass","org.apache.ws.scout.registry.ConnectionFactoryImpl");
//
//		Message esbMessage = MessageFactory.getInstance().getMessage();
//		System.out.println("acceptFriend ON MAIN WS ###"+id+" "+password+" "+idRel+"###");
//
//		HashMap requestMap = new HashMap();
//		requestMap.put("id",id);
//		requestMap.put("password",password);
//		requestMap.put("idRel",idRel);
//		esbMessage.getBody().add(requestMap);
//		
//		Message retMessage = null;
//
//		ServiceInvoker si;
//		try {
//			si = new ServiceInvoker("Relation", "acceptFriend");
//			retMessage = si.deliverSync(esbMessage, 10000L);
//			System.out.println("MAIN WS - Pedido de getSearch enviado\n");
//			
//			System.out.println("\n acceptFriend response is: "+retMessage.getBody().get(Body.DEFAULT_LOCATION));
//							 
//			
//			
//		} catch (MessageDeliverException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (FaultMessageException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (RegistryException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//	}
	
}
