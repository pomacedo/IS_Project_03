package eai;

import javax.ejb.Stateful;

import data.Client;

/**
 * Session Bean implementation class ManageBean
 */
@Stateful
public class ManageBean implements ManageBeanRemote {	
	
	private String email;
	private int id;
	private String password;
	private String name;
	private String photoPath;

    /**
     * Default constructor. 
     */
    public ManageBean() {
        // TODO Auto-generated constructor stub
    }
    
    public void setAll(ClientInfo client){
    	this.email=client.getEmail();
    	this.id=client.getId();
    	this.password=client.getPassword();
    	this.name=client.getName();
    	if(client.getPhotoPath()!=null)
    		this.photoPath=client.getPhotoPath();
    	else
    		this.photoPath="http://www.tutoresnarede.com.br/img/facebook-no-image.gif";
    }
    
    public void setAll(Client client){
    	this.email=client.getEmail();
    	this.id=client.getId();
    	this.password=client.getPassword();
    	this.name=client.getName();
    	if(client.getPhoto()!=null)
    		this.photoPath=client.getPhoto().getPath();
    	else
    		this.photoPath="http://www.tutoresnarede.com.br/img/facebook-no-image.gif";
    }

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhotoPath() {
		return photoPath;
	}

	public void setPhotoPath(String photoPath) {
		this.photoPath = photoPath;
	}
}
