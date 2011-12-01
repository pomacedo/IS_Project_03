package eai;

import java.io.Serializable;

public class ClientInfo implements Serializable{
	/**
	 * 
	 */	
	private static final long serialVersionUID = 1L;
	
	private String email;
	private int id;
	private String password;
	private String name;
	//private String photoPath;
	int idPhoto;
    /**
     * Default constructor. 
     */
    public ClientInfo() {
        // TODO Auto-generated constructor stub
    }

	public ClientInfo(String email, int id, String password, String name,int idPhoto) {
		super();
		this.email = email;
		this.id = id;
		this.password = password;
		this.name = name;
		this.idPhoto=idPhoto;
	}
	
	public ClientInfo(String email, int id, String password, String name) {
		super();
		this.email = email;
		this.id = id;
		this.password = password;
		this.name = name;
	}
	
	
	public int getIdPhoto() {
		return idPhoto;
	}

	public void setPhotoPath(int idPhoto) {
		this.idPhoto = idPhoto;
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

}
