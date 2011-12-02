package data;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Photo implements Serializable{
	
	private static final long serialVersionUID = 1L;	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String path;
	
	public Photo() {
		super();
		
	}
	public Photo(String path) {
		super();
		this.path = path;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	
	

}
