package connector;

import java.util.HashMap;
import java.util.Iterator;
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
			
			System.out.println("MAIN WS DE NOVO!!!!!!!\n");
			ClientInfo ret = (ClientInfo) retMessage.getBody().get(Body.DEFAULT_LOCATION);
			//Map responseMsg = (Map) retMessage.getBody().get(Body.DEFAULT_LOCATION);
			//System.out.println("\n ::::::::: "+responseMsg.toString());
//			if(responseMsg.size()==0)
//				return null;
//			 Iterator i = responseMsg.keySet().iterator();
//			 email =(String) responseMsg.get(i.next());
//			 int id = Integer.parseInt((String) responseMsg.get(i.next()));
//			 String name = (String) responseMsg.get(i.next());
//			 password = (String) responseMsg.get(i.next());
//			 //String photo = (String) responseMsg.get(i.next());
//			 		 
//			 
//			 ClientInfo temp = new ClientInfo();
//			 temp.setEmail(email);
//			 temp.setId(id);
//			 temp.setName(name);
//			 temp.setPassword(password);
			 //temp.setPhotoPath(Integer.parseInt(photo));
			 //temp.setPhotoPath(-1);
			 
			 System.out.println("\nMain WebService data received: "+ret.getEmail()+",,"+ret.getName()+",,"+ret.getId()+",,"+ret.getPassword()+",,"+ret.getIdPhoto());
			 
			
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
	public int addClient(String name,String password,String gender,String email){
		System.setProperty("javax.xml.registry.ConnectionFactoryClass","org.apache.ws.scout.registry.ConnectionFactoryImpl");

		Message esbMessage = MessageFactory.getInstance().getMessage();
		System.out.println("TOUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUU");
		System.out.println("VOU INVOCAR"+name+" | "+email+" | "+gender+" | "+password);
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
			System.out.println("\n ::::::::: "+responseMsg.toString());
			
			 Iterator i = responseMsg.keySet().iterator();
			 int response = Integer.parseInt((String) responseMsg.get(i.next()));
			 		 
			 
			 
			 System.out.println("\ncreateUser response is: "+response);
			 
			
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
	
}
