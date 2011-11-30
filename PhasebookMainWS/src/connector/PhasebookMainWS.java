package connector;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

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
	public ClientInfo test(String email,String password)
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
			
			System.out.println("Pedido no WS enviado\n");
			
			Map responseMsg = (Map) retMessage.getBody().get(Body.DEFAULT_LOCATION);
			System.out.println("\n ::::::::: "+responseMsg.toString());
			
			 Iterator i = responseMsg.keySet().iterator();
			 email =(String) responseMsg.get(i.next());
			 int id = Integer.parseInt((String) responseMsg.get(i.next()));
			 String name = (String) responseMsg.get(i.next());
			 password = (String) responseMsg.get(i.next());
			 String photo = (String) responseMsg.get(i.next());
			 		 
			 
			 ClientInfo temp = new ClientInfo();
			 temp.setEmail(email);
			 temp.setId(id);
			 temp.setName(name);
			 temp.setPassword(password);
			 temp.setPhotoPath(photo);
			 System.out.println("\nGGGGGG "+temp.getEmail()+",,"+temp.getName()+",,"+id+",,"+password+",,"+photo);
			 
//			Iterator i = responseMsg.keySet().iterator();
//			while(i.hasNext())
//			{
//				String s = i.next().toString();
//				System.out.println(s+" >>>> "+responseMsg.get(s)+" FIZ POUIS");
//			}
//				System.out.println("tessting: "+responseMsg.containsKey(s));
//				System.out.println("validatin "+requestMap.get(s).toString());
//				ArrayList<ClientInfo> res =(ArrayList<ClientInfo>) responseMsg.get("algoMaisResponse.ArrayList");
//				System.out.println("depos do get WS");
//				//System.out.println("&&&&&&&&"+res.get(0).getName());
//			}
			
			
			//System.out.println( "\n\n@@@@@@@@"+responseMsg.keySet().size());
			
			//System.out.println(responseMsg.get("algoMaisResponse.return.email"));
			//ClientInfo retfinal = (ClientInfo) responseMsg.get("algoMaisResponse.return");
			//String res = (String) responseMsg.get("algoMaisResponse.return");
			//System.out.println(retfinal.getName());
			//System.out.println("vamos sair="+res);
			
			return temp;
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
	
}
