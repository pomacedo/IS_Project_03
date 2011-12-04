package action;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.jboss.soa.esb.actions.AbstractActionLifecycle;
import org.jboss.soa.esb.helpers.ConfigTree;
import org.jboss.soa.esb.listeners.message.MessageDeliverException;
import org.jboss.soa.esb.message.Body;
import org.jboss.soa.esb.message.Message;

import data.Client;
import data.Relation;


import eai.ClientInfo;



public class responseHandler extends AbstractActionLifecycle
{
	
	 protected ConfigTree _config;

	 public responseHandler(ConfigTree config) {
	  _config = config;
	 }
	 public Message getRelations(Message message) throws MessageDeliverException
	 {
		 System.out.println("RESPONSE HANDLER - GET RELATIONS");
		 Map responseMsg = null;
		 responseMsg = (Map) message.getBody().get(Body.DEFAULT_LOCATION);		 
		 System.out.println(responseMsg.toString());
		 List<Integer> ids = new ArrayList<Integer>();
		 Iterator i = responseMsg.keySet().iterator();
		 while(i.hasNext())
			 ids.add(Integer.parseInt((String)responseMsg.get(i.next())));
		
		 message.getBody().add(ids);
		 System.out.println("RESPONSE HANDLER - GET RELATIONS ENDED");
		 return message;
	 }
	 
	 public Message getFriendsInfo(Message message) throws MessageDeliverException
	 {
		 System.out.println("RESPONSE HANDLER - GET FRIENDS INFO");
		 Map responseMsg = null;
		 responseMsg = (Map) message.getBody().get(Body.DEFAULT_LOCATION);
		 
		 System.out.println("getfriendsinfo to be handled:"+responseMsg.toString());
		 Iterator i = responseMsg.keySet().iterator();
		 List<Client> res = (List<Client>)responseMsg.get(i.next()); 
		 message.getBody().add(res);
		 System.out.println("RESPONSE HANDLER - GET FIRENDS INFO ENDED");
		 
		 return message;
	 }
	 
	 public Message getSearch(Message message) throws MessageDeliverException
	 {
		 System.out.println("RESPONSE HANDLER - GET SEARCH");
		 Map responseMsg = null;
		 responseMsg = (Map) message.getBody().get(Body.DEFAULT_LOCATION);
		 
		 System.out.println("GET SEACRH to be handled:"+responseMsg.toString());
		 List<Client> res = new ArrayList<Client>();
		 Iterator i = responseMsg.keySet().iterator();
		 String email="",name="",password="";
		 int id=0,idPhoto=-1;
		 while(i.hasNext())
		 {
			 email =(String) responseMsg.get(i.next());		 
			 char gender = (char) Integer.parseInt((String) responseMsg.get(i.next()));
			 id = Integer.parseInt((String) responseMsg.get(i.next()));
			 idPhoto = Integer.parseInt((String) responseMsg.get(i.next()));
			 double money = Double.parseDouble((String) responseMsg.get(i.next()));
			 name = (String) responseMsg.get(i.next());
			 password = (String) responseMsg.get(i.next());
			 Client c = new Client(name, password, email, money, gender);
			 c.setId(id);
			 c.setId_photo(idPhoto);
			 res.add(c);
		 }
		 message.getBody().add(res);
		 System.out.println("RESPONSE HANDLER - GET SEARCH ENDED");
		 
		 return message;
	 }
	 
	 public Message addFriend(Message message) throws MessageDeliverException
	 {
		 System.out.println("RESPONSE HANDLER - addFriend");
		 Map responseMsg = null;
		 responseMsg = (Map) message.getBody().get(Body.DEFAULT_LOCATION);
		 Iterator i = responseMsg.keySet().iterator();
		 boolean res = Boolean.parseBoolean((String)responseMsg.get(i.next()));
		 
		 System.out.println("RESULTADO DO ADD FRIEND NO RESPONSE HANDLER :"+res);
		 message.getBody().add(res);
		 System.out.println("ESB RESPONSE HANDLER- addFriend ENDED");
		 return message;
	 }
	 
	 
	 public Message checkLogInRsp(Message message) throws MessageDeliverException
	 {
		 System.out.println("RESPONSE HANDLER - CHECK LOGIN");
		 Map responseMsg = null;
		 responseMsg = (Map) message.getBody().get(Body.DEFAULT_LOCATION);
		 
		 System.out.println(responseMsg.toString());
		 String email="",name="",password="";
		 int id=0,photo=-1;
		 Iterator i = responseMsg.keySet().iterator();
		 email =(String) responseMsg.get(i.next());
		 id = Integer.parseInt((String) responseMsg.get(i.next()));
		 photo = Integer.parseInt((String) responseMsg.get(i.next()));
		 name = (String) responseMsg.get(i.next());
		 password = (String) responseMsg.get(i.next());
		 
		 ClientInfo temp = new ClientInfo(email,id,password,name,photo);
		 
		 
		 message.getBody().add(temp);
		 System.out.println("ESB RESPONSE HANDLER - CHECK LOG IN ENDED");
		 return message;
	 }
	 public Message getPosts(Message message) throws MessageDeliverException
	 {
		 Map responseMsg = null;
		 System.out.println("ESB RESNPONSE HANDLER - GET POSTS");
		 responseMsg = (Map) message.getBody().get(Body.DEFAULT_LOCATION);
		 System.out.println(responseMsg.toString());
		 Iterator i = responseMsg.keySet().iterator();
		 List<data.Message> result = new ArrayList<data.Message>();
		 while(i.hasNext())
		 {
			 data.Message cur = new data.Message();
			 String s = i.next().toString();			 
			 cur.setId(Integer.parseInt((String) responseMsg.get(s)));
			 cur.setId_client_from(Integer.parseInt((String)responseMsg.get(i.next())));			 
			 cur.setId_client_to(Integer.parseInt((String)responseMsg.get(i.next())));			 
			 cur.setId_photo(Integer.parseInt((String)responseMsg.get(i.next())));			 
			 cur.setIs_private(Boolean.parseBoolean((String)responseMsg.get(i.next())));			 
			 cur.setIs_read(Boolean.parseBoolean((String) responseMsg.get(i.next())));			 
			 DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
			 
			Date d;
			try {
				s = (String) responseMsg.get(i.next());
				d = (Date)formatter.parse(s);
				cur.setMsg_date(d);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				System.out.println("falhou PARSE DA DATA: ");
				
			}			 		 
			cur.setText((String) responseMsg.get(i.next()));		 
			 
			 result.add(cur);
		 }
		 System.out.println("ESB RESPONSE HANDLER - GET POSTS ENDED");
		 message.getBody().add(result);
		 return message;
	 }
	 public Message addPhoto(Message message) throws MessageDeliverException
	 {
		 Map responseMsg = null;
		 System.out.println("ESB HANDLE add Photo");
		 responseMsg = (Map) message.getBody().get(Body.DEFAULT_LOCATION);
		 System.out.println("Map= "+responseMsg.toString());
		 int id=-1;
		 Iterator i = responseMsg.keySet().iterator();
		 id = Integer.parseInt((String) responseMsg.get(i.next()));
		 message.getBody().add(id);
		 System.out.println("ACABOU O RESPONSE HANDLE E A PHOTO TEM O ID="+id);
		 return message;
	 }
	 
	 public Message getClientInfo(Message message) throws MessageDeliverException
	 {
		 System.out.println("ESB - RESPONSEHANDLER GET CLIENT INFO");
		 Map responseMsg = null;
		 responseMsg = (Map) message.getBody().get(Body.DEFAULT_LOCATION);
		 System.out.println("GET CLIENT INFO RESP: "+responseMsg.toString());
		 
		 String email="",name="",password="";
		 int id=0,idPhoto=-1;
		 Iterator i = responseMsg.keySet().iterator();
		 email =(String) responseMsg.get(i.next());		 
		 char gender = (char) Integer.parseInt((String) responseMsg.get(i.next()));
		 id = Integer.parseInt((String) responseMsg.get(i.next()));
		 idPhoto = Integer.parseInt((String) responseMsg.get(i.next()));
		 double money = Double.parseDouble((String) responseMsg.get(i.next()));
		 name = (String) responseMsg.get(i.next());
		 password = (String) responseMsg.get(i.next());
		 
		 Client temp = new Client(idPhoto, name, password, email, money, gender);
		 temp.setId(id);
		 System.out.println("lido: "+email+" ,"+gender+", "+id+" ,"+idPhoto+", "+money);
		 message.getBody().add(temp);
		 
		 return message;
	 }
	 
	 public Message editProfile(Message message) throws MessageDeliverException
	 {
		 System.out.println("ESB - RESPONSEHANDLER EDIT PROFILE");
		 Map responseMsg = null;
		 responseMsg = (Map) message.getBody().get(Body.DEFAULT_LOCATION);
		 Iterator i = responseMsg.keySet().iterator();
		 boolean res = Boolean.parseBoolean((String)responseMsg.get(i.next()));
		 
		 System.out.println("cenas"+res);
		 message.getBody().add(res);
		 System.out.println("ESB RESPONSE HANDLER- EDIT PROFILE ENDED");
		 return message;
	 }
	 
	 public Message getPendingRelations(Message message) throws MessageDeliverException
	 {
		 System.out.println("ESB - RESPONSEHANDLER GET PENDING RELATIONS");
		 Map responseMsg = null;
		 responseMsg = (Map) message.getBody().get(Body.DEFAULT_LOCATION);
		 System.out.println("TO HANDLE: "+responseMsg.toString());
		 List<Relation> res = new ArrayList<Relation>();
		 Iterator i = responseMsg.keySet().iterator();
		 while(i.hasNext())
		 {
			 int id = Integer.parseInt((String) responseMsg.get(i.next()));
			 int idFrom = Integer.parseInt((String) responseMsg.get(i.next()));
			 int idTo = Integer.parseInt((String) responseMsg.get(i.next()));
			 char status = (char) Integer.parseInt((String) responseMsg.get(i.next()));
			 Relation r = new Relation(idFrom, idTo);
			 r.setId(idTo);
			 r.setStatus(status);
			 res.add(r);
		 }
		 System.out.println("ESB - RESPONSEHANDLER GET PENDING RELATIONS ENDED");
		 message.getBody().add(res);
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
