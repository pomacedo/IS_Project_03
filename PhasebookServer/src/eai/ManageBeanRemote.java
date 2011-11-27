package eai;
import javax.ejb.Remote;

import data.Client;

@Remote
public interface ManageBeanRemote {
	public String getEmail();
	public void setEmail(String email);
	public int getId();
	public void setId(int id);
	public String getPassword();
	public void setPassword(String password);
	public String getName();
	public void setName(String name);
	public void setAll(ClientInfo client);
	public void setAll(Client client);
	public String getPhotoPath();
	public void setPhotoPath(String photoPath);
}
