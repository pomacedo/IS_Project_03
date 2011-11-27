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
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("PhasebookServer");
    	EntityManager em = emf.createEntityManager();
		EntityTransaction tx=em.getTransaction();
		Client c1 = em.find(Client.class,idFrom);
		if(!c1.getPassword().equals(password))
			return false;
		Client c2 = em.find(Client.class,idTo);
		
		Message msg = new Message(c1,c2,isPrivate,message);
		try{
			tx.begin();
			em.persist(msg);
			tx.commit();
		}catch(RollbackException ex){
			return false;
		}
		sendEmailNotification(idTo);
		return true;
	}
	
	public boolean sendMsg(int idFrom,String password,String message,int idTo,boolean isPrivate,String photoPath) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("PhasebookServer");
    	EntityManager em = emf.createEntityManager();
		EntityTransaction tx=em.getTransaction();
		
		Photo p = new Photo(photoPath);
		try{
			tx.begin();
			em.persist(p);
			tx.commit();
		}catch(RollbackException ex){
			return false;
		}
		
		p=(Photo)em.createQuery("select p from Photo p where p.path=?1").setParameter(1, p.getPath()).getSingleResult();
		
		
		Client c1 = em.find(Client.class,idFrom);
		if(!c1.getPassword().equals(password))
			return false;
		Client c2 = em.find(Client.class,idTo);
		
		Message msg = new Message(c1,c2,p,isPrivate,message);
		
		try{
			tx.begin();
			em.persist(msg);
			tx.commit();
		}catch(RollbackException ex){
			return false;
		}
		sendEmailNotification(idTo);
		return true;
	}
	

	@Override
	public List<Message> getPosts(int idViewer,String password){
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("PhasebookServer");
		EntityManager em = emf.createEntityManager();
		Client c1=em.find(Client.class, idViewer);
		EntityTransaction tx=em.getTransaction();
		if(!c1.getPassword().equals(password))
			return null;
		
		
		List<Message> list=(List<Message>) c1.getMessagesTo();
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
					return null;
				}
			}
		}
		return toGo;		
	}
	
	@Override
	public List<Message> getPosts(int idViewer,String password, int idTo){
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("PhasebookServer");
		EntityManager em = emf.createEntityManager();
		Client c1=em.find(Client.class, idViewer);
		if(!c1.getPassword().equals(password))
			return null;		
		
		Client c2=em.find(Client.class, idTo);
		
		
		List<Relation> rel= (List<Relation>) c1.getRelationFrom();
		rel.addAll(c1.getRelationTo());
		RelationBean rb = new RelationBean();
		boolean isFriend=rb.checkIfIsApprovedFriend(idViewer,password,rel, c2.getId());
		
		List<Message> msg = (List<Message>)c2.getMessagesTo();
		
		if(msg==null)
			return null;
		List<Message> toGo = new ArrayList<Message>();
		if(!isFriend)
		{
			for(Message m:msg)
			{
				if(!m.getIs_private())
					toGo.add(m);
			}
		}else{
			toGo.addAll(msg);
		}
		return toGo;		
	}
	
	public void deleteMessage(int id,String password,int idMsg)
	{
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("PhasebookServer");
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx=em.getTransaction();
		Client c1 = em.find(Client.class, id);
		if(!c1.getPassword().equals(password))
			return;
		Message m = em.find(Message.class, idMsg);		
		Photo p = m.getId_photo();
		Client c = m.getclient_to();
		if(m.getId_photo()!=null){
			if(c.getPhoto().getId()==m.getId_photo().getId()){
				c.setPhoto(null);
				try{				
					tx.begin();
					em.persist(c);
					tx.commit();
				}catch(RollbackException ex){
					return ;
				}
			}
		}
			try{
			tx.begin();
			em.remove(m);
			tx.commit();
		}catch(RollbackException ex){
			return ;
		}
		
		if(p!=null)
		{
			try{
				tx.begin();
				em.remove(p);
				tx.commit();
			}catch(RollbackException ex){
				return ;
			}
		}
		
	}
	
	@Override
	public void sendEmailNotification(int id) {
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("PhasebookServer");
		EntityManager em = emf.createEntityManager();
		Client c1 = em.find(Client.class, id);
		List<Message> msg = (List<Message>) c1.getMessagesTo();
		String toSend="Hello, "+c1.getName()+"\nYou have received the following new messages:";
		
		
		for(Message m: msg)
		{
			if(!m.getIs_read())
			{
				toSend+="\n...........................................................................................";
				toSend+="\n\nSended by: "+m.getclient_from().getName()+" , at "+m.getMsg_date().toString();
				String type="";
				if(m.getIs_private())	type = "Private";
				else 					type = "Public";
				toSend+=" tagged as "+type;
				toSend+="\n\t"+m.getText();
				if(m.getId_photo()!=null)
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
	    }
		
	}
}
