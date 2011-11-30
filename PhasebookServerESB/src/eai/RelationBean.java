package eai;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Properties;

import javax.ejb.Stateless;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.RollbackException;

import data.Client;
import data.Photo;
import data.Relation;

/**
 * Session Bean implementation class RelationBean
 */
@Stateless
public class RelationBean implements RelationBeanRemote {

    /**
     * Default constructor. 
     */
	@Override
	public List<Relation> checkMyFriends(int id, String password) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("PhasebookServer");
		EntityManager em = emf.createEntityManager();
		Client c1 = em.find(Client.class, id);
		if(!c1.getPassword().equals(password))
			return null;
		
		List<Relation> list=(List<Relation>)em.createQuery("SELECT r FROM Relation r WHERE r.id_client_from LIKE ?1 or r.id_client_to LIKE ?2").setParameter(1, id).setParameter(2, id).getResultList();
	
//		List<Relation> relList = (List<Relation>) c1.getRelationFrom();
//		relList.addAll( c1.getRelationTo() );
//		relList.size(); // Load Data
		return list;
		
	}
	
	@Override
	public boolean addFriend(int idFrom,String password, int idTo) {
		// TODO Auto-generated method stub
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("PhasebookServer");
		EntityManager em = emf.createEntityManager();
//		Client c1 = em.find(Client.class, idFrom);
//		if(!c1.getPassword().equals(password))
//			return false;
		EntityTransaction tx=em.getTransaction();
//		Client c2 = em.find(Client.class, idTo);
		
		Relation r = new Relation(idFrom,idTo);
		try{
			tx.begin();
			em.persist(r);
			tx.commit();
		}catch(RollbackException ex){
			return false;
		}
		sendNewFriendRequestMail(idFrom,idTo);
		return true;
	}
	
	@Override
	public boolean checkIfIsFriend(int id, String password,List<Relation> relList, int idFriend) {
		boolean isFriend=false; 
		if(relList!=null)
		{			
			for(Relation r: relList) {
				
				if( (idFriend == r.getId_client_from() || idFriend==r.getId_client_to()) && (r.getStatus()=='A' || r.getStatus()=='P')){
					isFriend=true; 
					break;
				}
			}
		}
		return isFriend;
	}
	
	@Override
	public boolean checkIfIsApprovedFriend(int id, String password,List<Relation> relList, int idFriend) {
		boolean isFriend=false; 
		if(relList!=null)
		{			
			for(Relation r: relList) {
				
				if( (idFriend == r.getId_client_from() || idFriend==r.getId_client_to()) && (r.getStatus()=='A')){
					isFriend=true; 
					break;
				}
			}
		}
		return isFriend;
	}

	@Override
	public int numberOfFriends(int id) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("PhasebookServer");
		EntityManager em = emf.createEntityManager();
		Client c1 = em.find(Client.class, id);
		List<Relation> list=(List<Relation>)em.createQuery("SELECT r FROM Relation r WHERE (r.id_client_from LIKE ?1 or r.id_client_to LIKE ?2) and (r.status like 'A')").setParameter(1, id).setParameter(2, id).getResultList();
//		Collection<Relation> rel = c1.getRelationFrom();
//		rel.addAll(c1.getRelationTo());
//		int total=0;
//		for(Relation r: rel){
//			if(r.getStatus()=='A'){
//				total++;
//			}
//		}
		return list.size();
	}
	
	
	@Override
	public List<Relation> getNewRequests(int id, String password) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("PhasebookServer");
		EntityManager em = emf.createEntityManager();
		List<Relation> list=(List<Relation>)em.createQuery("SELECT r FROM Relation r WHERE (r.id_client_from LIKE ?1 or r.id_client_to LIKE ?2) and (r.status like 'P')").setParameter(1, id).setParameter(2, id).getResultList();
//		Client c1 = em.find(Client.class, id);
//		if(!c1.getPassword().equals(password))
//			return null;
//		
//		List<Relation> rel = (List<Relation>) c1.getRelationTo();
//		rel.size();
//		List<Relation> toGo = new ArrayList<Relation>();
//		for(Relation r: rel)
//		{
//			if(r.getStatus() == 'P')
//				toGo.add(r);
//		}		
		return list;
	}
	
	@Override
	public void acceptFriend(int id, String password, int idRel) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("PhasebookServer");
		EntityManager em = emf.createEntityManager();
		Client c1 = em.find(Client.class, id);
		if(!c1.getPassword().equals(password))
			return;
		Relation r1=em.find(Relation.class,idRel);
		if(r1.getId_client_to() == id)
		{
			r1.setStatus('A');		
			EntityTransaction tx=em.getTransaction();
			try{
				tx.begin();
				em.persist(r1);
				tx.commit();
			}catch(RollbackException ex){
				return ;
			}		
		}		
	}

	@Override
	public void declineFriend(int id, String password, int idRel) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("PhasebookServer");
		EntityManager em = emf.createEntityManager();
		Client c1 = em.find(Client.class, id);
		if(!c1.getPassword().equals(password))
			return;
		Relation r1=em.find(Relation.class,idRel);
		EntityTransaction tx=em.getTransaction();
		try{
			tx.begin();
			em.remove(r1);
			tx.commit();
		}catch(RollbackException ex){
			return ;
		}

	}
	
	@Override
	public Relation getRelation(List<Relation> relList, int idFriend) {
		
		if(relList!=null)
		{			
			for(Relation r: relList) {
				
				if( (idFriend == r.getId_client_from() || idFriend==r.getId_client_to()) && (r.getStatus()=='A' || r.getStatus()=='P')){
					return r;
				}
			}
		}
		return null;
	}
	
	@Override
	public void sendNewFriendRequestMail(int cFrom, int cTo) {
		// TODO Auto-generated method stub
		String toSend="Hello, "+cTo+"\nYou have received a new friend request:";
		toSend+="\n\tFrom "+cFrom+"\n\n\tYou can accept or decline this request by acessing his request in your Friends list.";
		toSend+="This e-mail has been automatically generated, please do not reply.\n Phasebook Administration.";
		
		Properties props = new Properties();	       	    
	    
	    props.put("mail.transport.protocol", "smtps");
        props.put("mail.smtps.host", "smtp.gmail.com");
        props.put("mail.smtps.auth", "true");
        
	    Session session = Session.getDefaultInstance(props, null);
	    try {
	    	Session mailSession = Session.getDefaultInstance(props);
	        mailSession.setDebug(true);
	        Transport transport = mailSession.getTransport();

	        MimeMessage message = new MimeMessage(mailSession);
	        message.setSubject("Phasebook - New friend request");
	        message.setContent(toSend, "text/plain");
	        
	        
	        //TODO TIRAR A LEITURA DO CLIENT
	        EntityManagerFactory emf = Persistence.createEntityManagerFactory("PhasebookServer");
			EntityManager em = emf.createEntityManager();
	        Client paraTirar=em.find(Client.class,cTo);
	        em.close();
	        message.addRecipient(MimeMessage.RecipientType.TO,
	             new InternetAddress(paraTirar.getEmail()));

	        transport.connect
	          ("smtp.gmail.com", 465,"isphasebook@gmail.com", "duromacedoperdigao");

	        transport.sendMessage(message,
	            message.getRecipients(MimeMessage.RecipientType.TO));
	        transport.close();
	    } catch (MessagingException mex) {
	        System.out.println("send failed, exception: " + mex);
	    }
	}
	
	
	@Override
	public void removeFriend(int id, String password, int idFriend) {
		// TODO Auto-generated method stub
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("PhasebookServer");
		EntityManager em = emf.createEntityManager();
		Client c1 = em.find(Client.class, id);
		if(!c1.getPassword().equals(password))
			return;
//		Relation r1=getRelation((List<Relation>) c1.getRelationFrom(), idFriend);
//		if(r1==null)
//			r1=getRelation((List<Relation>) c1.getRelationTo(), idFriend);
		List<Relation> r1=(List<Relation>)em.createQuery("SELECT r FROM Relation r WHERE (r.id_client_from LIKE ?1 and r.id_client_to like ?2) or (r.id_client_to LIKE ?1 and r.id_client_from LIKE ?2)").setParameter(1, id).setParameter(2, idFriend).getResultList();
		if(r1==null)
			return;
		EntityTransaction tx=em.getTransaction();
		try{
			tx.begin();
			em.remove(r1);
			tx.commit();
		}catch(RollbackException ex){
			return ;
		}
	}
	
	@Override
	public List<Client> getFriends(int id) {
		
		
		//TODO esta tambem precisa de reevisao
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("PhasebookServer");
		EntityManager em = emf.createEntityManager();
		
//		Client c1 = em.find(Client.class,id);
		List<Client> result= new ArrayList<Client>();
		
//		for(Relation r:c1.getRelationFrom())// percorre as relacoes pedidas por o cliente x
//		{
//			if(r.getStatus()=='A')			// verifica se estas estao num estado aprovado
//				result.add(r.getId_client_to());
//		}
//		for(Relation r:c1.getRelationTo())	// percorre as relacoes aceites pelo cliente y
//		{
//			if(r.getStatus()=='A')			// verifica se estas estao num estado aprovado
//				result.add(r.getId_client_from());
//		}
		
		List<Relation> list=(List<Relation>)em.createQuery("SELECT r FROM Relation r WHERE (r.id_client_from LIKE ?1 or r.id_client_to LIKE ?2) and (r.status like 'A')").setParameter(1, id).setParameter(2, id).getResultList();
		for(Relation r:list)
		result.add(em.find(Client.class, r.getId_client_from()==id?r.getId_client_to():r.getId_client_from()));
		
		
		if(result.size()==0)
			return null;
		
		return result;
	}
	
}
