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
	@ManyToOne
	@JoinColumn(name="ID_CLIENT_FROM", referencedColumnName="ID")
	private Client client_from;
	@ManyToOne
	@JoinColumn(name="ID_CLIENT_TO", referencedColumnName="ID")
	private Client client_to;
	private char status;
	//private int id_client_from;
	//private int id_client_to;
	
		
	public Relation() {
		super();
	}

	public Relation(Client clientFrom,Client clientTo) {
		super();
		this.client_to = clientTo;
		this.client_from = clientFrom;
		this.status = 'P';
	}
	

	public int getId() {
		return id;
	}



	public void setId(int id) {
		this.id = id;
	}



	public Client getId_client_from() {
		return client_from;
	}

	public void setId_client_from(Client client_from) {
		this.client_from = client_from;
	}

	public Client getId_client_to() {
		return client_to;
	}

	public void setId_client_to(Client client_to) {
		this.client_to = client_to;
	}

	public char getStatus() {
		return status;
	}

	public void setStatus(char status) {
		this.status = status;
	}
}
