package data;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import org.apache.bsf.util.event.adapters.java_awt_event_ActionAdapter;

@Entity
public class Message implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	private int id;
	@ManyToOne
	@JoinColumn(name="ID_CLIENT_FROM", referencedColumnName="ID")
	private Client client_from;
	@ManyToOne
	@JoinColumn(name="ID_CLIENT_TO", referencedColumnName="ID")
	private Client client_to;
	
	@OneToOne
	@JoinColumn(name="ID_PHOTO", referencedColumnName="ID")
	private Photo photo;
	
	private Boolean is_private;
	private String text;
	private Boolean is_read;
	private Date msg_date;
	
	public Message(){
		super();
	}

	public Message(Client client_from, Client client_to,Boolean is_private, String text) {
		super();
		this.client_from = client_from;
		this.client_to = client_to;
		this.is_private = is_private;
		this.text = text;
		this.is_read = false;
		this.msg_date = new Date();
	}


	public Message(Client client_from, Client client_to, Photo photo,Boolean is_private, String text) {
		super();
		
		this.client_from = client_from;
		this.client_to = client_to;
		this.photo = photo;
		this.text = text;
		this.is_private = is_private;
		this.is_read = false;
		this.msg_date = new Date();
	}

	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Client getclient_from() {
		return client_from;
	}

	public void setclient_from(Client client_from) {
		this.client_from = client_from;
	}

	public Client getclient_to() {
		return client_to;
	}

	public void setclient_to(Client client_to) {
		this.client_to = client_to;
	}

	public Photo getId_photo() {
		return photo;
	}

	public void setId_photo(Photo photo) {
		this.photo = photo;
	}

	public Boolean getIs_private() {
		return is_private;
	}

	public void setIs_private(Boolean is_private) {
		this.is_private = is_private;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Boolean getIs_read() {
		return is_read;
	}

	public void setIs_read(Boolean is_read) {
		this.is_read = is_read;
	}

	public Date getMsg_date() {
		return msg_date;
	}

	public void setMsg_date(Date msg_date) {
		this.msg_date = msg_date;
	}
}
