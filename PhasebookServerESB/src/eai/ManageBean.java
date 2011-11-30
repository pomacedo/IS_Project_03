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
	private int idPhoto;

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
    	if(client.getIdPhoto()!=-1)
    		this.idPhoto=client.getIdPhoto();
//    	else
//    		this.photoPath="http://www.tutoresnarede.com.br/img/facebook-no-image.gif";
    }
    
    public void setAll(Client client){
    	this.email=client.getEmail();
    	this.id=client.getId();
    	this.password=client.getPassword();
    	this.name=client.getName();
    	if(client.getId_photo()!=-1)
    		this.idPhoto=client.getId_photo();
//    	else
//    		this.photoPath="http://www.tutoresnarede.com.br/img/facebook-no-image.gif";
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

	public int getIdPhoto() {
		return idPhoto;
	}

	public void setIdPhoto(int idPhoto) {
		this.idPhoto = idPhoto;
	}
}
