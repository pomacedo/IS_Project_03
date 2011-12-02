package example;

import java.util.List;

import javax.ejb.EJB;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

import data.Message;

import eai.MessageBeanRemote;


@WebService(name = "messagews", targetNamespace = "http://PhasebookWSs/messageWS")
public class messageWS {
	@EJB(mappedName = "MessageBean/remote")
	MessageBeanRemote msgBeanRem;	

	@WebMethod	
	public List<Message> getPosts(@WebParam(name = "idViewer") int idViewer,@WebParam(name = "password") String password,@WebParam(name = "idTo") int idTo){
		
		List<Message> result =null;
		if(idTo!=-1)
			result = msgBeanRem.getPosts(idViewer,password,idTo);
		else
			result = msgBeanRem.getPosts(idViewer, password);
		
		return result;
	}

	@WebMethod	
	public boolean sendMessage(@WebParam(name = "idFrom")int id_from,@WebParam(name = "password")String password,@WebParam(name = "text")String message,@WebParam(name = "idTo")int id_to,@WebParam(name = "isPrivate")boolean isPrivate,@WebParam(name = "idPhoto")int idPhoto){
		
		if(idPhoto!=-1)
			return msgBeanRem.sendMsg(id_from, password, message, id_to, isPrivate, idPhoto);
		else
			return msgBeanRem.sendMsg(id_from, password, message, id_to, isPrivate);
	}	
//	@WebMethod	
//	public void deleteMessage(@WebParam(name = "idViewer")String id,@WebParam(name = "password")String password,@WebParam(name = "idMsg")String idMsg){
//		msgBeanRem.deleteMessage(Integer.parseInt(id),password,Integer.parseInt(idMsg));
//	}
//	@WebMethod	
//	public void sendEmailNotification(@WebParam(name = "id") String id){
//		msgBeanRem.sendEmailNotification( Integer.parseInt(id));
//	}
	
}
