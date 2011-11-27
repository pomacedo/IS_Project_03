package data;


import java.io.Serializable;
import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.OrderBy;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContexts;
import javax.persistence.PersistenceUnitUtil;
import javax.persistence.Table;
import javax.persistence.OneToMany;
@Entity@Table(name="CLIENT")
public class Client implements Serializable{
	
	private static final long serialVersionUID = 1L;	
	@Id
	private int id;
	
	@OneToOne
	@JoinColumn(name="ID_PHOTO", referencedColumnName="ID")
	private Photo photo;
	private String name,password,email;
	private double money;
	private char gender;

	@OneToMany(mappedBy="client_from")
	private Collection<Relation> relationFrom;
	@OneToMany(mappedBy="client_to")
	private Collection<Relation> relationTo;

	@OneToMany(mappedBy="client_to")
	@OrderBy("msg_date DESC")
    private Collection<Message> messagesTo;
	@OneToMany(mappedBy="client_from")
	@OrderBy("msg_date DESC")
    private Collection<Message> messagesFrom;

	@OneToMany(mappedBy="clientFrom")
	@OrderBy("id DESC")
	private Collection<Bet> myBets;

	
	
	public Client() {
		super();
	}
		
	public Client(Photo photo, String name, String password,
			String email, double money, char gender) {
		super();
		this.photo = photo;
		this.name = name;
		this.password = password;
		this.email = email;
		this.money = money;
		this.gender = gender;
	}

	public Client(String name, String password, String email,
			double money, char gender) {
		super();
		this.name = name;
		this.password = password;
		this.email = email;
		this.money = money;
		this.gender = gender;
	}

	public Collection<Bet> getMyBets() {
		return myBets;
	}

	public void setMyBets(Collection<Bet> myBets) {
		this.myBets = myBets;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Photo getPhoto() {
		if(photo!=null)
			return photo;
		else 
			return new Photo("http://www.tutoresnarede.com.br/img/facebook-no-image.gif");
	}
	public void setPhoto(Photo photo) {
		this.photo = photo;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public double getMoney() {
		return money;
	}
	public void setMoney(double money) {
		this.money = money;
	}
	public char getGender() {
		return gender;
	}
	public void setGender(char gender) {
		this.gender = gender;
	}


	public Collection<Relation> getRelationFrom() {
		return relationFrom;
	}

	public void setRelationFrom(Collection<Relation> relationFrom) {
		this.relationFrom = relationFrom;
	}

	public Collection<Relation> getRelationTo() {
		return relationTo;
	}

	public void setRelationTo(Collection<Relation> relationTo) {
		this.relationTo = relationTo;

	}
	public Collection<Message> getMessagesTo() {
		return messagesTo;
	}

	public void setMessagesTo(Collection<Message> messagesTo) {
		this.messagesTo = messagesTo;
	}

	public Collection<Message> getMessagesFrom() {
		return messagesFrom;
	}

	public void setMessagesFrom(Collection<Message> messagesFrom) {
		this.messagesFrom = messagesFrom;

	}
	
}
