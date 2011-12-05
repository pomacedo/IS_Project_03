package action;
/*
* JBoss, Home of Professional Open Source
* Copyright 2006, JBoss Inc., and others contributors as indicated
* by the @authors tag. All rights reserved.
* See the copyright.txt in the distribution for a
* full listing of individual contributors.
* This copyrighted material is made available to anyone wishing to use,
* modify, copy, or redistribute it subject to the terms and conditions
* of the GNU Lesser General Public License, v. 2.1.
* This program is distributed in the hope that it will be useful, but WITHOUT A
* WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A
* PARTICULAR PURPOSE.  See the GNU Lesser General Public License for more details.
* You should have received a copy of the GNU Lesser General Public License,
* v.2.1 along with this distribution; if not, write to the Free Software
* Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston,
* MA  02110-1301, USA.
*
* (C) 2005-2006,
* @author JBoss Inc.
*/


import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.jboss.soa.esb.actions.AbstractActionLifecycle;
import org.jboss.soa.esb.addressing.eprs.LogicalEPR;
import org.jboss.soa.esb.client.ServiceInvoker;
import org.jboss.soa.esb.helpers.ConfigTree;
import org.jboss.soa.esb.listeners.message.MessageDeliverException;
import org.jboss.soa.esb.message.Body;
import org.jboss.soa.esb.message.Message;

import eai.ClientInfo;

public class myInitializer extends AbstractActionLifecycle
{

	 protected ConfigTree _config;
	
	 public myInitializer(ConfigTree config) {
	  _config = config;
	 }
	 
	 public Message getPosts(Message message) throws MessageDeliverException
	{
//		 System.out.println("ESB - INIT GET POSTS");
		 Map requestMsg = ((Map)message.getBody().get(Body.DEFAULT_LOCATION));
		 
	     int idViewer = (Integer)requestMsg.get("idViewer");
	     String password = (String)requestMsg.get("password");
	     int idTo = (Integer)requestMsg.get("idTo");
	     
	     Map send = new HashMap();
	     send.put("getPosts.idViewer", idViewer);
	     send.put("getPosts.password", password);
	     send.put("getPosts.idTo", idTo);
	     message.getBody().add(send);
	     
		 return message;
	}
	 
	public Message checkLogIn(Message message) throws MessageDeliverException
	{
//		 System.out.println("ESB - INIT LOGIN");
		 Map requestMsg = ((Map)message.getBody().get(Body.DEFAULT_LOCATION));
	     String email = (String)requestMsg.get("email");
	     String password = (String)requestMsg.get("password");
	    	     
	     Map send = new HashMap();
	     send.put("checkLogIn.email", email);
	     send.put("checkLogIn.password", password);
	     message.getBody().add(send);
	     
		return message;
	}
   
	public Message createUser(Message message) throws MessageDeliverException
	{
//		 System.out.println("ESB - CREATE USER\n");
		 Map requestMsg = ((Map)message.getBody().get(Body.DEFAULT_LOCATION));
		 
	     String email = (String)requestMsg.get("email");
	     String password = (String)requestMsg.get("password");
	     String name = (String)requestMsg.get("name");
	     String gender =  (String) requestMsg.get("gender");
	     
	     Map send = new HashMap();
	     send.put("createUser.email", email);
	     send.put("createUser.password", password);
	     send.put("createUser.name", name);
	     send.put("createUser.gender", gender);
	     message.getBody().add(send);
	     
		return message;
	}
	
	public Message getSearch(Message message) throws MessageDeliverException
	{
//		System.out.println("ESB INIT - GET SEARCH\n");
		 Map requestMsg = ((Map)message.getBody().get(Body.DEFAULT_LOCATION));
		 
	     String searchFor = (String)requestMsg.get("searchFor");	     
	     
	     Map send = new HashMap();
	     send.put("getSearch.searchFor", searchFor);
	     
	     message.getBody().add(send);
//	     System.out.println("ESB INIT - GET SEARCH EDNDED\n");
		return message;
	}
	
	public Message getClientInfo(Message message) throws MessageDeliverException
	{
//		 System.out.println("ESB - INIT GET CLIENT INFO");
		 Map requestMsg = ((Map)message.getBody().get(Body.DEFAULT_LOCATION));
		 
	     int id = (Integer) requestMsg.get("id");	     
	     
	     Map send = new HashMap();
	     send.put("getClientInfo.id", id);
	     
	     message.getBody().add(send);
//	     System.out.println("ESB INIT - GET CLIENT INFO ENDEED");
		return message;
	}
	
	public Message getFriendsInfo(Message message) throws MessageDeliverException
	{
		 System.out.println("ESB - INIT GET FRIENDS INFO");
		 Map requestMsg = ((Map)message.getBody().get(Body.DEFAULT_LOCATION));
		 
	     List<Integer> ids = (List<Integer>) requestMsg.get("ids");	
//	     List<String> newIDS = new ArrayList<String>();
//	     for(int i:ids)
//	    	 newIDS.add(""+i);
	     
	     Map send = new HashMap();
	     send.put("getFriendsInfo.ids", ids);
	     message.getBody().add(send);
	     
	     System.out.println("ESB INIT - GET FRIENDS INFO ENDEED ..... "+((List<Integer>)send.get("getFriendsInfo.ids")).get(0));
		return message;
	}
	
	public Message getRelations(Message message) throws MessageDeliverException
	{
		 System.out.println("ESB - INIT GET RELATIONS");
		 Map requestMsg = ((Map)message.getBody().get(Body.DEFAULT_LOCATION));
		 
	     int id = (Integer) requestMsg.get("id");	     
	     
	     Map send = new HashMap();
	     send.put("getRelations.id", id);
	     
	     message.getBody().add(send);
	     System.out.println("ESB INIT - GET RELATIONS ENDEED");
		return message;
	}
	
	public Message addFriend(Message message) throws MessageDeliverException
	{
//		 System.out.println("ESB -------- INIT add Friend");
		 Map requestMsg = ((Map)message.getBody().get(Body.DEFAULT_LOCATION));
		 
	     int id = (Integer) requestMsg.get("id");
	     String password = (String) requestMsg.get("password");
	     int idTo = (Integer) requestMsg.get("idTo");
	     
	     Map send = new HashMap();
	     send.put("addFriend.id", id);
	     send.put("addFriend.password", password);
	     send.put("addFriend.idTo", idTo);
	     
	     message.getBody().add(send);
//	     System.out.println("ESB INIT - add Friend ENDEED");
		return message;
	}
	
	public Message acceptFriend(Message message) throws MessageDeliverException
	{
//		 System.out.println("ESB -------- INIT accept Friend");
		 Map requestMsg = ((Map)message.getBody().get(Body.DEFAULT_LOCATION));
		 
	     int id = (Integer) requestMsg.get("id");
	     String password = (String) requestMsg.get("password");
	     int idRel = (Integer) requestMsg.get("idRel");
	     
	     Map send = new HashMap();
	     send.put("acceptFriend.id", id);
	     send.put("acceptFriend.password", password);
	     send.put("acceptFriend.idRel", idRel);
	     
	     message.getBody().add(send);
//	     System.out.println("ESB INIT - accept Friend ENDEED");
		return message;
	}
	
	public Message removeFriend(Message message) throws MessageDeliverException
	{
		 System.out.println("ESB -----######--- INIT remove Friend");
		 Map requestMsg = ((Map)message.getBody().get(Body.DEFAULT_LOCATION));
		 
	     int id = (Integer) requestMsg.get("id");
	     String password = (String) requestMsg.get("password");
	     int idRel = (Integer) requestMsg.get("idRel");
	     
	     Map send = new HashMap();
	     send.put("removeFriend.id", id);
	     send.put("removeFriend.password", password);
	     send.put("removeFriend.idRel", idRel);
	     
	     message.getBody().add(send);
	     System.out.println("ESB INIT ------###----- remove Friend ENDEED");
		return message;
	}
	
	public Message declineFriend(Message message) throws MessageDeliverException
	{
		 System.out.println("ESB -------- INIT decline Friend");
		 Map requestMsg = ((Map)message.getBody().get(Body.DEFAULT_LOCATION));
		 
	     int id = (Integer) requestMsg.get("id");
	     String password = (String) requestMsg.get("password");
	     int idRel = (Integer) requestMsg.get("idRel");
	     
	     Map send = new HashMap();
	     send.put("declineFriend.id", id);
	     send.put("declineFriend.password", password);
	     send.put("declineFriend.idRel", idRel);
	     
	     message.getBody().add(send);
	     System.out.println("ESB INIT - decline Friend ENDEED");
		return message;
	}
	
	public Message editProfile(Message message) throws MessageDeliverException
	{
//		System.out.println("init edit profile");
		 Map requestMsg = ((Map)message.getBody().get(Body.DEFAULT_LOCATION));
		 
	     String email = (String)requestMsg.get("email");
	     String password = (String)requestMsg.get("password");
	     String name = (String)requestMsg.get("name");
	     String gender =  (String) requestMsg.get("gender");	     
	     String newPassword=(String) requestMsg.get("newPassword");	     
	     int id = (Integer) requestMsg.get("id");	     
	     float money = (Float)requestMsg.get("money");	     
	     
	     Map send = new HashMap();
	     send.put("editProfile.email", email);
	     send.put("editProfile.password", password);
	     send.put("editProfile.name", name);
	     send.put("editProfile.gender", gender);
	     send.put("editProfile.id", id);
	     send.put("editProfile.newPassword", newPassword);
	     send.put("editProfile.money",money);
	     message.getBody().add(send);
//	     System.out.println("VOU SAIR DO INIT EDIT PROFILE");
		return message;
	}
	
	public Message sendMessage(Message message) throws MessageDeliverException
	{
//		System.out.println("ESB - INIT SEND MESSAGE");
		 Map requestMsg = ((Map)message.getBody().get(Body.DEFAULT_LOCATION));
//		 System.out.println("");System.out.println("INIT SEND MESSAGE\nMAP: "+requestMsg.toString()); 
		 int idFrom = (Integer)requestMsg.get("idFrom");		 
		 String password = (String) requestMsg.get("password");		 
	     String text = (String)requestMsg.get("message");	     
	     int idTo = (Integer)requestMsg.get("idTo");	     
	     boolean isPrivate= (Boolean)requestMsg.get("isPrivate");	     
	     int idPhoto = (Integer)requestMsg.get("idPhoto");	     
	     
	     Map send = new HashMap();
	     send.put("sendMessage.idFrom", idFrom);
	     send.put("sendMessage.password", password);
	     send.put("sendMessage.text", text);
	     send.put("sendMessage.idTo", idTo);
	     send.put("sendMessage.isPrivate", isPrivate);
	     send.put("sendMessage.idPhoto", idPhoto);
	     message.getBody().add(send);	
//	     System.out.println("FIM INIT SEND MESSAGE");
	     return message;    
	}
	
	public Message addPhoto(Message message) throws MessageDeliverException
	{
//		 System.out.println("INIT ADD PHOTO");
		 Map requestMsg = ((Map)message.getBody().get(Body.DEFAULT_LOCATION));
		 
	     String path = (String)requestMsg.get("path");	     
	     
	     Map send = new HashMap();
	     send.put("addPhoto.photoPath", path);
	     
	     message.getBody().add(send);
//	     System.out.println("INIT ADD PHOTO ENDED");
		return message;
	}
	
	public Message getPendingRelations(Message message) throws MessageDeliverException
	{
//		 System.out.println("INIT GET PENDING RELATIONS");
		 Map requestMsg = ((Map)message.getBody().get(Body.DEFAULT_LOCATION));
		 String password = (String) requestMsg.get("password");		 
	     String id = (String) requestMsg.get("id");	
	     Map send = new HashMap();
	     send.put("getNewFriendRequest.id",Integer.parseInt(id));
	     send.put("getNewFriendRequest.password",password);
	     message.getBody().add(send);
//		 System.out.println("INIT GET PENDING RELATIONS ENDED"+id+",,,"+password);
		 return message;
	}
	
}