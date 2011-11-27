package com.sample.action;

import javax.naming.InitialContext;

import org.jbpm.graph.def.ActionHandler;
import org.jbpm.graph.def.Transition;
import org.jbpm.graph.exe.ExecutionContext;

import eai.ClientSessionBeanRemote;
import eai.ManageBeanRemote;

public class DoLoginActionHandler  implements ActionHandler {
	private static final long serialVersionUID = 1L;
	
	String message;
	public void execute(ExecutionContext context) throws Exception {
		
		//context.getContextInstance().setVariable("username", message);
		System.out.println("################ do login action handler!!!!!!: ");
		context.setVariable("result", "FUNCIONOU");
		Transition t = new Transition();
		context.setTransition(t);
		//context.getContextInstance().setVariable("message", message);
//		InitialContext ctx = new InitialContext();
//		ClientSessionBeanRemote cb=(ClientSessionBeanRemote)ctx.lookup("ClientSessionBean/remote");
		
		
	}

}
