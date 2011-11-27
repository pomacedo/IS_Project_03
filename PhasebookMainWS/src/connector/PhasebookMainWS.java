package connector;
import java.util.HashMap;
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

@WebService
public class PhasebookMainWS {

	
	@WebMethod
	public String test(String email,String password)
	{
		System.out.println("TOU NO MAIN WS");
		System.setProperty("javax.xml.registry.ConnectionFactoryClass","org.apache.ws.scout.registry.ConnectionFactoryImpl");

		Message esbMessage = MessageFactory.getInstance().getMessage();
		HashMap requestMap = new HashMap();
		requestMap.put("loginUser.email",email);
		requestMap.put("loginUser.password",password);
		esbMessage.getBody().add(requestMap);
		
		Message retMessage = null;

		ServiceInvoker si;
		try {
			si = new ServiceInvoker("Authentication", "test");
			retMessage = si.deliverSync(esbMessage, 10000L);
			System.out.println("Pedido no WS enviado\n");
			HashMap responseMsg = (HashMap) retMessage.getBody().get(Body.DEFAULT_LOCATION);			
			return (String)responseMsg.get("algoMaisResponse.return");
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
