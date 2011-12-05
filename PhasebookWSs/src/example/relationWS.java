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
		
		return relBeanRem.getNewRequests(id,password);		
	}
	
	@WebMethod
	public boolean addFriend(@WebParam(name = "id") int id,@WebParam(name = "password") String password,@WebParam(name = "idTo") int idTo){
		return relBeanRem.addFriend( id, password, idTo);
	}
	@WebMethod
	public void declineFriend(@WebParam(name = "id") int id,@WebParam(name = "password") String password,@WebParam(name = "idRel") int idRel){
		relBeanRem.declineFriend(id, password, idRel);
	}
	@WebMethod
	public void removeFriend(@WebParam(name = "id") int id,@WebParam(name = "password") String password,@WebParam(name = "idRel") int idRel){
		relBeanRem.removeFriend(id, password, idRel);
	}
	@WebMethod	
	public void acceptFriend(@WebParam(name = "id") int id,@WebParam(name = "password")  String password, @WebParam(name = "idRel") int idRel){
		relBeanRem.acceptFriend( id, password, idRel);
	}
	

}
