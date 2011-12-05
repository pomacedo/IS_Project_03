package eai;

import java.util.ArrayList;
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

import data.Bet;
import data.Client;
import data.Message;
import data.Photo;
import data.Relation;

import eai.*;

/**
 * Session Bean implementation class MessageBean
 */
@Stateless 
public class MessageBean implements MessageBeanRemote {
 
    /**
     * Default constructor. 
     */
    public MessageBean() {
        // TODO Auto-generated constructor stub
    }
    
    public boolean sendMsg(int idFrom,String password,String message,int idTo,boolean isPrivate) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("PhasebookServerESB");
    	EntityManager em = emf.createEntityManager();
		EntityTransaction tx=em.getTransaction();
		Client c1 = em.find(Client.class,idFrom);
		if(!c1.getPassword().equals(password)){
			em.close();
			return false;
		}
		Client c2 = em.find(Client.class,idTo);
		
		Message msg = new Message(c1.getId(),c2.getId(),isPrivate,message);
		try{
			tx.begin();
			em.persist(msg);
			tx.commit();
		}catch(RollbackException ex){
			em.close();
			return false;
		}
		sendEmailNotification(idTo);
		em.close();
		return true;
	}
	
	public boolean sendMsg(int idFrom,String password,String message,int idTo,boolean isPrivate,int idPhoto) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("PhasebookServerESB");
    	EntityManager em = emf.createEntityManager();
		EntityTransaction tx=em.getTransaction();
		
		Client c1 = em.find(Client.class,idFrom);
		if(!c1.getPassword().equals(password)){
			em.close();
			return false;
		}
		
		Message msg = new Message(idFrom,idTo,idPhoto,isPrivate,message);
		
		try{
			tx.begin();
			em.persist(msg);
			tx.commit();
		}catch(RollbackException ex){
			em.close();
			return false;
		}
		sendEmailNotification(idTo);
		em.close();
		return true;
	}
	

	@Override
	public List<Message> getPosts(int idViewer,String password){
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("PhasebookServerESB");
		EntityManager em = emf.createEntityManager();
		Client c1=em.find(Client.class, idViewer);
		EntityTransaction tx=em.getTransaction();
		if(!c1.getPassword().equals(password)){
			em.close();
			return null;
		}
		
		List<Message> list=(List<Message>)em.createQuery("SELECT m FROM Message m WHERE m.id_client_to LIKE ?1 order by m.msg_date desc").setParameter(1, c1.getId()).getResultList();
//		List<Message> list=(List<Message>) c1.getMessagesTo();
		List<Message> toGo = new ArrayList<Message>();
		for(Message m:list)
		{
			toGo.add(m);
			if(!m.getIs_read())
			{
				m.setIs_read(true);
				try{
					tx.begin();
					em.persist(m);
					tx.commit();
				}catch(RollbackException ex){
					em.close();
					return null;
				}
			}
		}
		
		em.close();
		return toGo;		
	}
	
	@Override
	public List<Message> getPosts(int idViewer,String password, int idTo){
		
		//TODO É preciso filtrar o return deste metodo para verificar as relacoes de amizade
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("PhasebookServerESB");
		EntityManager em = emf.createEntityManager();
		Client c1=em.find(Client.class, idViewer);
		if(!c1.getPassword().equals(password)){
			em.close();
			return null;
		}
		
		Client c2=em.find(Client.class, idTo);
		
		List<Message> list=(List<Message>)em.createQuery("SELECT m FROM Message m WHERE m.id_client_to LIKE ?1").setParameter(1, c1.getId()).getResultList();
		
//		List<Relation> rel= (List<Relation>) c1.getRelationFrom();
//		rel.addAll(c1.getRelationTo());
//		RelationBean rb = new RelationBean();
//		boolean isFriend=rb.checkIfIsApprovedFriend(idViewer,password,rel, c2.getId());
//		
//		List<Message> msg = (List<Message>)c2.getMessagesTo();
//		
//		if(msg==null){
//			em.close();
//			return null;
//		}
//		List<Message> toGo = new ArrayList<Message>();
//		if(!isFriend)
//		{
//			for(Message m:msg)
//			{
//				if(!m.getIs_private())
//					toGo.add(m);
//			}
//		}else{
//			toGo.addAll(msg);
//		}
		em.close();
		return list;		
	}
	
	public void deleteMessage(int id,String password,int idMsg)
	{
		
		//TODO É preciso apagar as fotos (atencao ao facto que pode ser foto de perfil) (no caso de existirem) antes de apagar as mensagens
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("PhasebookServerESB");
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx=em.getTransaction();
		Client c1 = em.find(Client.class, id);
		if(!c1.getPassword().equals(password)){
			em.close();
			return;
		}
		Message m = em.find(Message.class, idMsg);		
		try{
			tx.begin();
			em.remove(m);
			tx.commit();
		}catch(RollbackException ex){
			em.close();
			return ;
		}
		em.close();
	}
	
	@Override
	public void sendEmailNotification(int id) {
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("PhasebookServerESB");
		EntityManager em = emf.createEntityManager();
		Client c1 = em.find(Client.class, id);
		List<Message> list=(List<Message>)em.createQuery("SELECT m FROM Message m WHERE m.id_client_to LIKE ?1").setParameter(1, c1.getId()).getResultList();
//		List<Message> msg = (List<Message>) c1.getMessagesTo();
		String toSend="Hello, "+c1.getName()+"\nYou have received the following new messages:";
		
		
		for(Message m: list)
		{
			if(!m.getIs_read())
			{
				toSend+="\n...........................................................................................";
				toSend+="\n\nSended by: "+m.getId_client_from()+" , at "+m.getMsg_date().toString();
				String type="";
				if(m.getIs_private())	type = "Private";
				else 					type = "Public";
				toSend+=" tagged as "+type;
				toSend+="\n\t"+m.getText();
				if(m.getId_photo()!=-1)
					toSend+="\n\nHave photo included.";
				toSend+="\n\n";
			}
		}
		
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
	        message.setSubject("Phasebook - New unreaded messages");
	        message.setContent(toSend, "text/plain");

	        message.addRecipient(MimeMessage.RecipientType.TO,
	             new InternetAddress(c1.getEmail()));

	        transport.connect
	          ("smtp.gmail.com", 465,"isphasebook@gmail.com", "duromacedoperdigao");

	        transport.sendMessage(message,
	            message.getRecipients(MimeMessage.RecipientType.TO));
	        transport.close();
	    } catch (MessagingException mex) {
	        System.out.println("send failed, exception: " + mex);
	        em.close();
	    }
	    em.close();
	}
	
	
	
	@Override
 	public int privNumberOfPhotos(int id){
 		EntityManagerFactory emf = Persistence.createEntityManagerFactory("PhasebookServerESB");
 		EntityManager em = emf.createEntityManager();
 		List<Message> list=(List<Message>)em.createQuery("SELECT m FROM Message m WHERE m.id_client_to LIKE ?1").setParameter(1, id).getResultList();
 		int count=0;
 		for(Message m:list)
 		{
 			if(m.getId_photo()!=-1)
 				count++;
 		}
 		return count;
 	}
	
	// Devolve o numero de fotos publicas
 	@Override
 	public int pubNumberOfPhotos(int id){
 		EntityManagerFactory emf = Persistence.createEntityManagerFactory("PhasebookServerESB");
 		EntityManager em = emf.createEntityManager();
 		List<Message> list=(List<Message>)em.createQuery("SELECT m FROM Message m WHERE m.id_client_to LIKE ?1").setParameter(1, id).getResultList();
 		int count=0;
 		for(Message m:list)
 		{
 			if(m.getId_photo()!=-1 && !m.getIs_private())
 				count++;
 		}
 		return count;
 	}

	@Override
	public List<Integer> getAllIdPhotos(int idViewer, String password, int idTo,boolean isFriends) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("PhasebookServerESB");
 		EntityManager em = emf.createEntityManager();
 		List<Integer> list;
 		if(isFriends)
 			list=(List<Integer>)em.createQuery("SELECT m.id_photo FROM Message m WHERE m.id_client_to LIKE ?1 and m.id_photo != -1").setParameter(1, idTo).getResultList();
 		else
 			list=(List<Integer>)em.createQuery("SELECT m.id_photo FROM Message m WHERE m.id_client_to LIKE ?1 and m.is_private is false and m.id_photo != -1").setParameter(1, idTo).getResultList();
 		
		return list;
	}	
 	
}
