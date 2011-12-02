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
//	@WebMethod	
//	public boolean sendMsg(@WebParam(name = "id_from") String id_from,@WebParam(name = "password") String password,@WebParam(name = "message") String message,@WebParam(name = "id_to") String id_to,@WebParam(name = "isPrivate") String isPrivate){
//		return msgBeanRem.sendMsg(Integer.parseInt(id_from), password, message, Integer.parseInt(id_to), Boolean.parseBoolean(isPrivate));
//	}
//	@WebMethod	
//	public boolean sendMsg(@WebParam(name = "id_from")String id_from,@WebParam(name = "password")String password,@WebParam(name = "message")String message,@WebParam(name = "id_to")String id_to,@WebParam(name = "isPrivate")String isPrivate,@WebParam(name = "photoPath")String photoPath){
//		return msgBeanRem.sendMsg(Integer.parseInt(id_from), password, message, Integer.parseInt(id_to), Boolean.parseBoolean(isPrivate), photoPath);
//	}	
//	@WebMethod	
//	public void deleteMessage(@WebParam(name = "idViewer")String id,@WebParam(name = "password")String password,@WebParam(name = "idMsg")String idMsg){
//		msgBeanRem.deleteMessage(Integer.parseInt(id),password,Integer.parseInt(idMsg));
//	}
//	@WebMethod	
//	public void sendEmailNotification(@WebParam(name = "id") String id){
//		msgBeanRem.sendEmailNotification( Integer.parseInt(id));
//	}
	
}
