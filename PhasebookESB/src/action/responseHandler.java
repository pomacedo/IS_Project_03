package action;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.jboss.soa.esb.actions.AbstractActionLifecycle;
import org.jboss.soa.esb.helpers.ConfigTree;
import org.jboss.soa.esb.listeners.message.MessageDeliverException;
import org.jboss.soa.esb.message.Body;
import org.jboss.soa.esb.message.Message;


import eai.ClientInfo;

public class responseHandler extends AbstractActionLifecycle
{
	
	 protected ConfigTree _config;

	 public responseHandler(ConfigTree config) {
	  _config = config;
	 }
		 
	 public Message checkLogInRsp(Message message) throws MessageDeliverException
	 {
		 Map responseMsg = null;
		 responseMsg = (Map) message.getBody().get(Body.DEFAULT_LOCATION);
		 System.out.println("RESPONSE HANDLER"+responseMsg.toString());
		 String email="",name="",password="";
		 int id=0,photo=-1;
		 Iterator i = responseMsg.keySet().iterator();
		 email =(String) responseMsg.get(i.next());
		 id = Integer.parseInt((String) responseMsg.get(i.next()));
		 photo = Integer.parseInt((String) responseMsg.get(i.next()));
		 name = (String) responseMsg.get(i.next());
		 password = (String) responseMsg.get(i.next());
		 
		 ClientInfo temp = new ClientInfo(email,id,password,name,photo);
		 
		 //System.out.println("\nGGGGGG "+temp.getEmail()+",,"+temp.getName()+",,"+id+",,"+password+",,");
		 message.getBody().add(temp);
		 
		 return message;
	 }
	 
	 
//	 public Message getSearch(Message message) throws MessageDeliverException
//	 {
//		 Map responseMsg = (Map) message.getBody().get(Body.DEFAULT_LOCATION);
//		 System.out.println("ResponseHandler map: "+responseMsg.toString());
//		 System.out.println("");
//		 Iterator i = responseMsg.keySet().iterator();
//		 while (i.hasNext())
//		 {
//			 Client temp = new Client();
//			 
//			 String email =(String) responseMsg.get(i.next());
//			 int id = Integer.parseInt((String) responseMsg.get(i.next()));
//			 String name = (String) responseMsg.get(i.next());
//			 String password = (String) responseMsg.get(i.next());
//			 String photo = (String) responseMsg.get(i.next());
//			 temp.setEmail(email);
//			 temp.setId(id);
//			 temp.setName(name);
//			 temp.setPassword(password);
//			 //to be continued
//		 }
//		 
//		 
////		 System.out.println("\nGGGGGG "+temp.getEmail()+",,"+temp.getName()+",,"+id+",,"+password+",,"+photo);
////		 message.getBody().add(temp);
//		 
//		 return message;
//	 }
}
