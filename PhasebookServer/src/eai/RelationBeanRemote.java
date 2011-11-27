package eai;
import java.util.List;

import javax.ejb.Remote;

import data.Client;
import data.Relation;

@Remote
public interface RelationBeanRemote {

	
	public boolean addFriend(int idFrom,String password, int idTo);
	public List<Relation> checkMyFriends(int id, String password);
	public boolean checkIfIsFriend(int id, String password,List<Relation> relList, int idFriend);
	public boolean checkIfIsApprovedFriend(int id, String password,List<Relation> relList, int idFriend);
	public int numberOfFriends(int id);
	public List<Relation> getNewRequests(int id,String password);
	public void acceptFriend(int id, String password, int idRel);
	public void declineFriend(int id, String password, int idRel);
	public void removeFriend(int id, String password, int idFriend);
	public Relation getRelation(List<Relation> relList, int idFriend);
	public void sendNewFriendRequestMail(Client c1, Client c2);
	public List<Client> getFriends(int id);
}
