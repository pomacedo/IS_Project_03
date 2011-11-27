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
	public String test(String something)
	{
		System.out.println("TOU NO MAIN WS");
		System.setProperty("javax.xml.registry.ConnectionFactoryClass","org.apache.ws.scout.registry.ConnectionFactoryImpl");

		Message esbMessage = MessageFactory.getInstance().getMessage();

		esbMessage.getBody().add(something);
		Message retMessage = null;

		ServiceInvoker si;
		try {
			si = new ServiceInvoker("Authentication", "test");
			retMessage = si.deliverSync(esbMessage, 10000L);
			//Map responseMsg = (Map) retMessage.getBody().get(Body.DEFAULT_LOCATION);
			return (String) retMessage.getBody().get();
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
