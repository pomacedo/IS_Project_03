package example;

import java.util.List;

import javax.ejb.EJB;
import javax.jws.*;

import data.Relation;

import eai.*;

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
	public List<Relation> getNewFriendRequest(@WebParam(name = "id") int id,@WebParam(name = "password") String password)
	{
		System.out.println("STARTED GETPENDING RELATIONS "+id+"|"+password);		
		return relBeanRem.getNewRequests(id,password);		
	}
	
	@WebMethod Integer teste(@WebParam(name="id")int id, @WebParam(name="password")String pass)
	{
		System.out.println("cuucu WHOOOOOOOOOOOOOOAAAAAAAAAAAAAAAHHHHHHHHHH!!!"+id+" "+pass);
		return 1000;
	}
	@WebMethod
	public boolean addFriend(@WebParam(name = "id") int id,@WebParam(name = "password") String password,@WebParam(name = "idTo") int idTo){
		return relBeanRem.addFriend( id, password, idTo);
	}
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
