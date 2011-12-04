package example;

import java.util.List;

import javax.ejb.EJB;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

import data.Client;
import data.Relation;

import eai.RelationBeanRemote;

@WebService(name = "relationws", targetNamespace = "http://PhasebookWSs/relationWS")
public class relationWS {
	@EJB(mappedName = "RelationBean/remote")
	RelationBeanRemote relBeanRem;
	
	
	@WebMethod	
	public List<Integer> getRelations(@WebParam(name = "id") int id)
	{
		return relBeanRem.getFriends(id);		
	}
	@WebMethod	
	public List<Relation> getPendingRelations(@WebParam(name = "id") int id,@WebParam(name = "password") String password)
	{
		return relBeanRem.getNewRequests(id,password);		
	}
	
//	@WebMethod
//	public boolean addFriend(String idFrom,String password, String idTo){
//		return relBeanRem.addFriend( Integer.parseInt(idFrom), password,  Integer.parseInt(idTo));
//	}
//	@WebMethod	
//	public List<Relation> checkMyFriends(String id, String password){
//		return relBeanRem.checkMyFriends( Integer.parseInt(id),  password);
//	}
//	@WebMethod	
//	public boolean checkIfIsFriend(String id, String password,List<Relation> relList, String idFriend){
//		return relBeanRem.checkIfIsFriend( Integer.parseInt(id),  password,relList,  Integer.parseInt(idFriend));
//		}
//	@WebMethod	
//	public boolean checkIfIsApprovedFriend(String id, String password,List<Relation> relList, String idFriend){
//		return relBeanRem.checkIfIsApprovedFriend( Integer.parseInt(id),  password,relList,  Integer.parseInt(idFriend));
//		}
//	@WebMethod	
//	public int numberOfFriends(String id){
//		return relBeanRem.numberOfFriends( Integer.parseInt(id));
//		}
//	@WebMethod	
//	public List<Relation> getNewRequests(String id,String password){
//		return relBeanRem.getNewRequests( Integer.parseInt(id), password);
//		}
//	@WebMethod	
//	public void acceptFriend(String id, String password, String idRel){
//		relBeanRem.acceptFriend( Integer.parseInt(id),  password,  Integer.parseInt(idRel));
//		}
//	@WebMethod	
//	public void declineFriend(String id, String password, String idRel){
//		relBeanRem.declineFriend( Integer.parseInt(id),  password, Integer.parseInt(idRel));
//		}
//	@WebMethod	
//	public void removeFriend(String id, String password, String idFriend){
//		relBeanRem.removeFriend( Integer.parseInt(id),  password,  Integer.parseInt(idFriend));
//		}
//	@WebMethod	
//	public Relation getRelation(List<Relation> relList, String idFriend){
//		return relBeanRem.getRelation(relList,  Integer.parseInt(idFriend));
//		}
//	@WebMethod	
//	public void sendNewFriendRequestMail(Client c1, Client c2){
//		relBeanRem.sendNewFriendRequestMail(c1,c2);
//		}
//	@WebMethod
//	public List<Client> getFriends(String id){
//		return relBeanRem.getFriends(Integer.parseInt(id));
//		}

}
