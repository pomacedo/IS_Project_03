package action;

import java.util.Iterator;
import java.util.Map;

import org.jboss.soa.esb.actions.AbstractActionLifecycle;
import org.jboss.soa.esb.helpers.ConfigTree;
import org.jboss.soa.esb.listeners.message.MessageDeliverException;
import org.jboss.soa.esb.message.Body;
import org.jboss.soa.esb.message.Message;

import data.Client;

import eai.ClientInfo;


public class responseHandler extends AbstractActionLifecycle
{
	
	 protected ConfigTree _config;

	 public responseHandler(ConfigTree config) {
	  _config = config;
	 }
	
	 
	 public Message checkLogInRsp(Message message) throws MessageDeliverException
	 {
		 Map responseMsg = (Map) message.getBody().get(Body.DEFAULT_LOCATION);
		 System.out.println(responseMsg.toString());
		 
		 Iterator i = responseMsg.keySet().iterator();
		 String email =(String) responseMsg.get(i.next());
		 int id = Integer.parseInt((String) responseMsg.get(i.next()));
		 String name = (String) responseMsg.get(i.next());
		 String password = (String) responseMsg.get(i.next());
		 String photo = (String) responseMsg.get(i.next());
		 		 
		 
		 ClientInfo temp = new ClientInfo();
		 temp.setEmail(email);
		 temp.setId(id);
		 temp.setName(name);
		 temp.setPassword(password);
		 temp.setPhotoPath(photo);
		 System.out.println("\nGGGGGG "+temp.getEmail()+",,"+temp.getName()+",,"+id+",,"+password+",,"+photo);
		 message.getBody().add(temp);
		 
		 return message;
	 }
	 
	 
	 public Message getSearch(Message message) throws MessageDeliverException
	 {
		 Map responseMsg = (Map) message.getBody().get(Body.DEFAULT_LOCATION);
		 System.out.println(responseMsg.toString());
		 
		 Iterator i = responseMsg.keySet().iterator();
		 while (i.hasNext())
		 {
			 Client temp = new Client();
			 
			 String email =(String) responseMsg.get(i.next());
			 int id = Integer.parseInt((String) responseMsg.get(i.next()));
			 String name = (String) responseMsg.get(i.next());
			 String password = (String) responseMsg.get(i.next());
			 String photo = (String) responseMsg.get(i.next());
			 temp.setEmail(email);
			 temp.setId(id);
			 temp.setName(name);
			 temp.setPassword(password);
			 //to be continued
		 }
		 
		 
//		 System.out.println("\nGGGGGG "+temp.getEmail()+",,"+temp.getName()+",,"+id+",,"+password+",,"+photo);
//		 message.getBody().add(temp);
		 
		 return message;
	 }
}
