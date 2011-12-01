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
	@EJB(mappedName = "MessageBeanRemote/remote")
	MessageBeanRemote msgBeanRem;
	
//	
//	@WebMethod	
//	public List<Message> getPosts(@WebParam(name = "idViewer") String idViewer,@WebParam(name = "password") String password){
//		return msgBeanRem.getPosts(Integer.parseInt(idViewer),password);
//	}
//	@WebMethod	
//	public List<Message> getPosts(@WebParam(name = "idViewer") String idViewer,@WebParam(name = "password") String password,@WebParam(name = "idTo") String idTo){
//		return msgBeanRem.getPosts(Integer.parseInt(idViewer),password,Integer.parseInt(idTo));
//	}
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
