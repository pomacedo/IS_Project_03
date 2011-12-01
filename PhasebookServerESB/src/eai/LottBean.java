package eai;

import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.RollbackException;

import data.Bet;
import data.Lottery;

/**
 * Session Bean implementation class LottBean
 */
@Stateless
public class LottBean implements LottBeanRemote {

    /**
     * Default constructor. 
     */
    public LottBean() {
        // TODO Auto-generated constructor stub
    }

	@Override
	public Lottery getCurrentLottery() {
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("PhasebookServer");
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx=em.getTransaction();
		List<Lottery> list=(List<Lottery>)em.createQuery("select l from Lottery l where l.result=-1").getResultList();
		if(list.size()>0){
			if(list.get(0).getDate().compareTo(new Date())>0)
				return list.get(0);
			else
				return null;
		}
		else		
			return null;
	}

	@Override
	public int updateResult(int number) {
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("PhasebookServer");
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx=em.getTransaction();
		List<Lottery> list=(List<Lottery>)em.createQuery("select l from Lottery l where l.result=-1").getResultList();
		if(list.size()>0){
			Lottery oldL=list.get(0);
			oldL.setResult(number);
			try{
				tx.begin();
				em.persist(oldL);
				tx.commit();
			}catch(RollbackException ex){
				return -1;
			}
			return oldL.getId();
		}	
		return -1;
	}

	@Override
	public void checkWinners(int idL) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("PhasebookServer");
		EntityManager em = emf.createEntityManager();
		Lottery l = em.find(Lottery.class, idL);
		List<Bet> betList=(List<Bet>)em.createQuery("SELECT b FROM Bet b WHERE b.id_lottery LIKE ?1").setParameter(1,idL).getResultList();
		ClientSessionBean cb = new ClientSessionBean();
		for(Bet b: betList)
		{
			if(b.getNumber()==l.getResult())
			{
				
				cb.betWon(b.getId_clientFrom());
			}
		}
		
	}

	@Override
	public boolean createNextLottery(Date nextDraw) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("PhasebookServer");
		EntityManager em = emf.createEntityManager();
		Lottery newL=new Lottery(nextDraw);
		EntityTransaction tx=em.getTransaction();
		try{
			tx.begin();
			em.persist(newL);
			tx.commit();
		}catch(RollbackException ex){
			return false;
		}		
		return true;
	}

	
}
