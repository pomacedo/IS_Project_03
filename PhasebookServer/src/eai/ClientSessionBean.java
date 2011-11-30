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
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("PhasebookServer");
		EntityManager em = emf.createEntityManager();             

		
		Client client=new Client(name, password, email, 50, gender);          
		EntityTransaction tx=em.getTransaction();
		try{   	   
			tx.begin();
			em.persist(client);
			tx.commit();
		}catch(RollbackException ex){
			return 0;
		}
		
		return 1;
	}

	public ClientInfo checkLogIn(String email, String password) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("PhasebookServer");
		EntityManager em = emf.createEntityManager();
		List<Client> list=(List<Client>)em.createQuery("SELECT c FROM Client c WHERE c.email LIKE ?1 and c.password like ?2").setParameter(1, email).setParameter(2,password).getResultList();
		
		if(list.size()==0)
			return null;
		else{
			if(list.get(0).getPhoto()==null)
				return new ClientInfo(list.get(0).getEmail(), list.get(0).getId(), list.get(0).getPassword(), list.get(0).getName());
			else
				return new ClientInfo(list.get(0).getEmail(), list.get(0).getId(), list.get(0).getPassword(), list.get(0).getName(),list.get(0).getPhoto().getPath());
		}
	}

	@Override
	public Client getClientInfo(int id) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("PhasebookServer");
		EntityManager em = emf.createEntityManager();
		Client client = em.find(Client.class, id);
		return client;
	}

	@Override
	public List<Client> getSearch(String searchfor) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("PhasebookServer");
		EntityManager em = emf.createEntityManager();
		List<Client> list=(List<Client>)em.createQuery("SELECT c FROM Client c where c.name LIKE '"+"%"+searchfor+"%'").getResultList();
		if(list.size()==0)
			return null;
		return list;
	}

	@Override
	public boolean editProfile(int id, String password, String name, String email, String newPassword, char gender, float money) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("PhasebookServer");
		EntityManager em = emf.createEntityManager();
		Client c1 = em.find(Client.class, id);
		if(!c1.getPassword().equals(password))
			return false;
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
			return false;
		}
		
		return true;
	}

	@Override
	public boolean betWon(int id) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("PhasebookServer");
		EntityManager em = emf.createEntityManager();
		Client c1 = em.find(Client.class, id);
		if(c1==null)
			return false;
		c1.setMoney(c1.getMoney()+50.0);
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

	@Override
	public String sayHello(String email, String password) {
		
		System.out.println("\nHELOOOOOOOOOO SOU UM EJB\n");
		System.out.println("\nEmail:"+email);
		System.out.println("\nPassword:"+password);
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("PhasebookServer");
		EntityManager em = emf.createEntityManager();
		List<Client> list=(List<Client>)em.createQuery("SELECT c FROM Client c WHERE c.email LIKE ?1 and c.password like ?2").setParameter(1, email).setParameter(2,password).getResultList();
		
		if(list.size()==0)
			{
			System.out.println("\nnao ta ninguem");
			return "NINGUEM";
			}
		else{
			if(list.get(0).getPhoto()==null)
			{
				System.out.println("\n sem photo");
				return ""+list.get(0).getId();
			}
			else
			{
				System.out.println("\n com photo");
				return ""+list.get(0).getId();
			}
		}
		
		
	}
}