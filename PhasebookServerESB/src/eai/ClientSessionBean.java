package eai;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date; 
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

import javax.ejb.Stateless;
import javax.persistence.*;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import data.*;
import data.Message;

/**
 * Session Bean implementation class ClientSessionBean 
 */
@Stateless
public class ClientSessionBean implements ClientSessionBeanRemote {
	/**
     * Default constructor. 
     */
    public ClientSessionBean() {
        // TODO Auto-generated constructor stub
    }

    public int addClient(String name, String password, char gender,String email) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("PhasebookServerESB");
		EntityManager em = emf.createEntityManager();             

		
		Client client=new Client(name, password, email, 50, gender);          
		EntityTransaction tx=em.getTransaction();
		try{   	   
			tx.begin();
			em.persist(client);
			tx.commit();
		}catch(RollbackException ex){
			em.close();
			return 0;
		}
		em.close();
		return 1;
	}

	public ClientInfo checkLogIn(String email, String password) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("PhasebookServerESB");
		EntityManager em = emf.createEntityManager();
		List<Client> list=(List<Client>)em.createQuery("SELECT c FROM Client c WHERE c.email LIKE ?1 and c.password like ?2").setParameter(1, email).setParameter(2,password).getResultList();
		
		if(list.size()==0){
			em.close();
			return null;
		}else{
//			if(list.get(0).getId_photo()==-1){
//				ClientInfo c=new ClientInfo(list.get(0).getEmail(), list.get(0).getId(), list.get(0).getPassword(), list.get(0).getName());
//				em.close();
//				return c;
//			}else{
			ClientInfo c=new ClientInfo(list.get(0).getEmail(), list.get(0).getId(), list.get(0).getPassword(), list.get(0).getName(),list.get(0).getId_photo());
			System.out.println("CLient ----> photo:"+c.getIdPhoto());
			em.close();
			return c;
//			}
		}
	}

	@Override
	public List<Client> getVariousClientInfo(List<Integer> ids)
	{
		List<Client> res = new ArrayList<Client>();
		for(int i: ids)
		{
			res.add(getClientInfo(i));
		}
		System.out.println("SESSIONBEAN : func getVariousClientInfo returned : "+res);
		return res;
	} 
	
	@Override
	public Client getClientInfo(int id) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("PhasebookServerESB");
		EntityManager em = emf.createEntityManager();
		Client client = em.find(Client.class, id);
		em.close();
		return client;
	}

	@Override
	public List<Client> getSearch(String searchfor) {
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("PhasebookServerESB");
		EntityManager em = emf.createEntityManager();
		List<Client> list=(List<Client>)em.createQuery("SELECT c FROM Client c where c.name LIKE '"+"%"+searchfor+"%'").getResultList();
		if(list.size()==0){
			em.close();
			return null;
		}
		em.close();
		return list;
	}

	@Override
	public boolean editProfile(int id, String password, String name, String email, String newPassword, char gender, float money) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("PhasebookServerESB");
		EntityManager em = emf.createEntityManager();
		Client c1 = em.find(Client.class, id);
		if(!c1.getPassword().equals(password)){
			em.close();
			return false;
		}
		if(newPassword.length()>0)
			c1.setPassword(newPassword);
		if(name.length()>0)
			c1.setName(name);
		
			c1.setGender(gender);
		if(money>0.0)
			c1.setMoney(money);
		
		EntityTransaction tx=em.getTransaction();
		try{   	   
			tx.begin();
			em.persist(c1);
			tx.commit();
		}catch(RollbackException ex){
			em.close();
			return false;
		}
		em.close();
		return true;
	}

	@Override
	public boolean betWon(int id) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("PhasebookServerESB");
		EntityManager em = emf.createEntityManager();
		Client c1 = em.find(Client.class, id);
		if(c1==null){
			em.close();
			return false;
		}
		c1.setMoney(c1.getMoney()+50.0);
		EntityTransaction tx=em.getTransaction();
		try{   	   
			tx.begin();
			em.persist(c1);
			tx.commit();
		}catch(RollbackException ex){
			em.close();
			return false;
		}
		em.close();
		return true;
	}
	
	
	// muda o id da foto de perfil para idPhoto do cliente com id =id
 	@Override
 	public boolean setProfilePhoto(int id, String password, int idPhoto) {
 		EntityManagerFactory emf = Persistence.createEntityManagerFactory("PhasebookServerESB");
 		EntityManager em = emf.createEntityManager();
 		Client c1 = em.find(Client.class, id);
 		if(!c1.getPassword().equals(password))
 			return false;
		c1.setId_photo(idPhoto);
 		EntityTransaction tx=em.getTransaction();
 		try{
 			tx.begin();
 			em.persist(c1);
 			tx.commit();
 		}catch(RollbackException ex){
 			return false;
 		}	
 		return true;
 	}
}