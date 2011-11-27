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


import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.jboss.soa.esb.actions.AbstractActionLifecycle;
import org.jboss.soa.esb.addressing.eprs.LogicalEPR;
import org.jboss.soa.esb.client.ServiceInvoker;
import org.jboss.soa.esb.helpers.ConfigTree;
import org.jboss.soa.esb.listeners.message.MessageDeliverException;
import org.jboss.soa.esb.message.Message;

public class teste01 extends AbstractActionLifecycle
{

 protected ConfigTree _config;

 public teste01(ConfigTree config) {
  _config = config;
 }

 public Message visit(Message message) throws MessageDeliverException {
  System.out.println("---------------------------------- VISIT ESB ACTION----------------------------------");
  String who = (String) message.getBody().get();
  
  message.getBody().add(" Hello " + who + ". You WERE IN THE ESB");
//  LogicalEPR lepr = new LogicalEPR(message.getHeader().getCall().getReplyTo());
//  ServiceInvoker si = lepr.getServiceInvoker();
//  si.deliverAsync(message);  
  System.out.println("-------------------------------- VISIT hello ESBACTION--------------------------------");
  
  
  return message;
 }
 

}