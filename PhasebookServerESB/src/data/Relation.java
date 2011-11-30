package data;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class Relation implements Serializable {
	
	private static final long serialVersionUID = 1L;
	@Id
	private int id;
	private int id_client_from;
	private int id_client_to;
	private char status;
	
		
	public Relation() {
		super();
	}

	public Relation(int id_client_from,int id_client_to) {
		super();
		this.id_client_to = id_client_to;
		this.id_client_from = id_client_from;
		this.status = 'P';
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getId_client_from() {
		return id_client_from;
	}

	public void setId_client_from(int id_client_from) {
		this.id_client_from = id_client_from;
	}

	public int getId_client_to() {
		return id_client_to;
	}

	public void setId_client_to(int id_client_to) {
		this.id_client_to = id_client_to;
	}

	public char getStatus() {
		return status;
	}

	public void setStatus(char status) {
		this.status = status;
	}
}
