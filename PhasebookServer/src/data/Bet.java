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
	
	@ManyToOne
	@JoinColumn(name="ID_LOTTERY", referencedColumnName="ID")
	private Lottery lottery;
			
	@ManyToOne
	@JoinColumn(name="ID_CLIENT", referencedColumnName="ID")
	private Client clientFrom;
	
	
	private int number;
	
	public Bet(){
		super();
	}	

	public Bet( Lottery lottery, Client client, int number) {
		super();
		
		this.lottery = lottery;
		this.clientFrom = client;
		this.number = number;
	}



	public Lottery getLottery() {
		return lottery;
	}



	public void setLottery(Lottery lottery) {
		this.lottery = lottery;
	}



	public Client getClient() {
		return clientFrom;
	}



	public void setClient(Client client) {
		this.clientFrom = client;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}


	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}
}
