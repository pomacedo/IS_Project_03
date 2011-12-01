package data;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;

@Entity
public class Lottery implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(name = "LOTTARY_DATE")
	private Date date;
	
	private int result;
	
//	@OneToMany(mappedBy="lottery")
//	@OrderBy("id DESC")
//	private Collection<Bet> thisBets;
	
	public Lottery(){
		super();
	}

	public Lottery(Date date) {
		super();
		this.date = date;
		this.result=-1;
	}

//	public Collection<Bet> getThisBets() {
//		return thisBets;
//	}
//
//	public void setThisBets(Collection<Bet> thisBets) {
//		this.thisBets = thisBets;
//	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public int getResult() {
		return result;
	}

	public void setResult(int result) {
		this.result = result;
	}
}
