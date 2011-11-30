package data;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

@Entity
public class Bet implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private int id_lottery;
	private int id_clientFrom;	
	private int number;
	
	public Bet(){
		super();
	}	

	public Bet(int id_lottery, int id_clientFrom, int number) {
		super();
		
		this.id_lottery = id_lottery;
		this.id_clientFrom = id_clientFrom;
		this.number = number;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getId_lottery() {
		return id_lottery;
	}

	public void setId_lottery(int id_lottery) {
		this.id_lottery = id_lottery;
	}

	public int getId_clientFrom() {
		return id_clientFrom;
	}

	public void setId_clientFrom(int id_clientFrom) {
		this.id_clientFrom = id_clientFrom;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}	
}
