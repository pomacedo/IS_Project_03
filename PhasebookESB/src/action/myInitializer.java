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
		 System.out.println("ESB - INIT LOGIN");
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
		 System.out.println("ESB - CREATE USER\n");
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
		 
		 Map requestMsg = ((Map)message.getBody().get(Body.DEFAULT_LOCATION));
		 
	     String searchFor = (String)requestMsg.get("searchfor");	     
	     
	     Map send = new HashMap();
	     send.put("getSearch.searchfor", searchFor);
	     
	     message.getBody().add(send);
	     
		return message;
	}
	
	public Message getClientInfo(Message message) throws MessageDeliverException
	{
		 
		 Map requestMsg = ((Map)message.getBody().get(Body.DEFAULT_LOCATION));
		 
	     int id = Integer.parseInt((String)requestMsg.get("id"));	     
	     
	     Map send = new HashMap();
	     send.put("getClientInfo.id", id);
	     
	     message.getBody().add(send);
	     
		return message;
	}
	
	
	public Message editProfile(Message message) throws MessageDeliverException
	{
		
		 Map requestMsg = ((Map)message.getBody().get(Body.DEFAULT_LOCATION));
		 
	     String email = (String)requestMsg.get("email");
	     String password = (String)requestMsg.get("password");
	     String name = (String)requestMsg.get("name");
	     String gender =  (String) requestMsg.get("gender");
	     String newPassword=(String) requestMsg.get("newPassword");
	     int id = Integer.parseInt((String) requestMsg.get("id"));
	     float money = Float.parseFloat((String)requestMsg.get("money"));
	     
	     Map send = new HashMap();
	     send.put("editProfile.email", email);
	     send.put("editProfile.password", password);
	     send.put("editProfile.name", name);
	     send.put("editProfile.gender", gender);
	     send.put("editProfile.id", id);
	     send.put("editProfile.newPassword", newPassword);
	     send.put("editProfile.money",money);
	     message.getBody().add(send);
	     
		return message;
	}
	
// public Message visit(Message message) throws MessageDeliverException {
//  System.out.println("\nCUCUCU");
//  Map responseMsg = (Map) message.getBody().get(Body.DEFAULT_LOCATION);  
//  
//  
//  //System.out.println(" Map is: " + responseMsg);
// 
////  HashMap <String,ArrayList> getObjectosResponseMap  = new HashMap <String,ArrayList>();
////  
//  Iterator i = responseMsg.keySet().iterator();
//  ArrayList<ClientInfo> toMainWS = new ArrayList<ClientInfo>();
//  while(i.hasNext())
//  {
//	  String s = i.next().toString();
//	  System.out.println("\n##################\nESB ACTION "+s+" >>>>> "+responseMsg.get(s));
//  }
//  
//  //System.out.println("\n<<<<<<< "+responseMsg.get("algoMaisResponse[1].name"));
//  System.out.println("\n\n\n @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
////  getObjectosResponseMap.put("algoMaisResponse.return",(ArrayList) responseMsg.get("algoMaisResponse"));
////  message.getBody().add(getObjectosResponseMap);
//  
//  return message;
// }
 

}