package eai;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.RollbackException;

import data.Bet;
import data.Client;
import data.Lottery;

/**
 * Session Bean implementation class BetBean
 */
@Stateless
public class BetBean implements BetBeanRemote {

    /**
     * Default constructor. 
     */ 
    public BetBean() {
        // TODO Auto-generated constructor stub
    }

	@Override
	public boolean makeNewBet(int id, String password, int idBet, int number) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("PhasebookServerESB");
		EntityManager em = emf.createEntityManager();
		Client c1 = em.find(Client.class, id);
		if(!c1.getPassword().equals(password)){
			em.close();
			return false;
		}
		
		LottBean lb = new LottBean();
		Lottery curLot=lb.getCurrentLottery();
		if(curLot==null){
			em.close();
			return false;
		}
		if(idBet!=curLot.getId() || c1.getMoney()-1<0){
			em.close();
			return false;
		}else{
			Bet newB = new Bet(curLot.getId(), c1.getId(), number);
			EntityTransaction tx=em.getTransaction();
			try{
				tx.begin();
				em.persist(newB);
				tx.commit();
			}catch(RollbackException ex){
				em.close();
				return false;
			}
			c1.setMoney(c1.getMoney()-1);
			try{
				tx.begin();
				em.persist(c1);
				tx.commit();
			}catch(RollbackException ex){
				
				try{
					tx.begin();
					em.remove(newB);
					tx.commit();
				}catch(RollbackException ex2){
					em.close();
					return false;
				}
				em.close();
				return false;
			}
			em.close();
			return true;
		}
	}

	@Override
	public List<Bet> getMyCurrentBets(int id, String password, Lottery curLot) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("PhasebookServerESB");
		EntityManager em = emf.createEntityManager();
		Client c1 = em.find(Client.class, id);
		if(!c1.getPassword().equals(password)){
			em.close();
			return null;
		}
		List<Bet> list=(List<Bet>)em.createQuery("SELECT b FROM Bet b WHERE b.id_clientFrom LIKE ?1").setParameter(1, c1.getId()).getResultList();
		List<Bet> toGo=new ArrayList<Bet>();
		if(list!=null)
		{
			for(Bet b:list)
			{
				if(b.getId_lottery()==curLot.getId())
					toGo.add(b);
			}
		}		
		em.close();
		return toGo;
	}

	@Override
	public List<Bet> getMyOldBets(int id, String password,Lottery curLot) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("PhasebookServerESB");
		EntityManager em = emf.createEntityManager();
		Client c1 = em.find(Client.class, id);
		if(!c1.getPassword().equals(password)){
			em.close();
			return null;
		}
		List<Bet> list=(List<Bet>)em.createQuery("SELECT b FROM Bet b WHERE b.id_clientFrom LIKE ?1").setParameter(1, c1.getId()).getResultList();
		List<Bet> toGo=new ArrayList<Bet>();
		
		if(list!=null)
		{
			if(curLot==null){
				toGo.addAll(list);
				em.close();
				return toGo;
			}
			for(Bet b:list)
			{
				if(b.getId_lottery()!=curLot.getId())
					toGo.add(b);
			}
		}		
		em.close();
		return toGo;
	}
}
