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
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private int id_client_from;
	
	private int id_client_to;
	
	private int id_photo;
	
	private Boolean is_private;
	private String text;
	private Boolean is_read;
	private Date msg_date;
	
	public Message(){
		super();
	}

	public Message(int id_client_from, int id_client_to,Boolean is_private, String text) {
		super();
		this.id_client_from = id_client_from;
		this.id_client_to = id_client_to;
		this.is_private = is_private;
		this.text = text;
		this.is_read = false;
		this.msg_date = new Date();
	}


	public Message(int id_client_from, int id_client_to, int id_photo,Boolean is_private, String text) {
		super();
		
		this.id_client_from = id_client_from;
		this.id_client_to = id_client_to;
		this.id_photo = id_photo;
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

	public int getId_photo() {
		return id_photo;
	}

	public void setId_photo(int id_photo) {
		this.id_photo = id_photo;
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
