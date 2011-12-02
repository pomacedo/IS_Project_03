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
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;	
	private int id_photo;
	private String name,password,email;
	private double money;
	private char gender;	
	
	public Client() {
		super();
	}
		
	public Client(int id_photo, String name, String password,
			String email, double money, char gender) {
		super();
		this.id_photo = id_photo;
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
		this.id_photo=-1;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getId_photo() {
		return id_photo;
	}

	public void setId_photo(int id_photo) {
		this.id_photo = id_photo;
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
}
