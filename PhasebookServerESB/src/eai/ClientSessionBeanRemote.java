package eai;

import java.util.List;

import javax.ejb.Remote;
import data.Client;
import data.Message;
import data.Photo;
import data.Relation;

@Remote
public interface ClientSessionBeanRemote {
	public int addClient(String name,String password,char gender,String email);
	public List<Client> getSearch(String searchfor);
	public Client getClientInfo(int id);
	public ClientInfo checkLogIn(String email,String password);
	public boolean editProfile(int id,String password,String name,String email,String newPassword,char gender, float money);
	public boolean betWon(int id);
	public boolean setProfilePhoto(int id,String password,int idPhoto);
}