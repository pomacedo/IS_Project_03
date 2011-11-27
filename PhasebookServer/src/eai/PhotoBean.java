package eai;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.RollbackException;

import data.Client;
import data.Message;
import data.Photo;

/**
 * Session Bean implementation class PhotoBean
 */
@Stateless
public class PhotoBean implements PhotoBeanRemote {

    /**
     * Default constructor. 
     */
    public PhotoBean() {
        // TODO Auto-generated constructor stub
    }

 // Devolve o numero de fotos privadas + publicas
 	@Override
 	public int privNumberOfPhotos(int id){
 		EntityManagerFactory emf = Persistence.createEntityManagerFactory("PhasebookServer");
 		EntityManager em = emf.createEntityManager();
 		Client c1 = em.find(Client.class, id);
 		Collection<Message> msgs= c1.getMessagesTo();
 		int count=0;
 		for(Message m:msgs)
 		{
 			if(m.getId_photo()!=null)
 				count++;
 		}
 		return count;
 	}
 	
 	// Devolve o numero de fotos publicas
 	@Override
 	public int pubNumberOfPhotos(int id){
 		EntityManagerFactory emf = Persistence.createEntityManagerFactory("PhasebookServer");
 		EntityManager em = emf.createEntityManager();
 		Client c1 = em.find(Client.class, id);
 		Collection<Message> msgs= c1.getMessagesTo();
 		int count=0;
 		for(Message m:msgs)
 		{
 			if(m.getId_photo()!=null && !m.getIs_private())
 				count++;
 		}
 		return count;
 	}
 	
 	// retorna uma lista de fotos publicas e privadas(consuante a amizade) respectivas ao user com id x
 	@Override
 	public List<Photo> getPhotos(int id, boolean isFriend) {
 		EntityManagerFactory emf = Persistence.createEntityManagerFactory("PhasebookServer");
 		EntityManager em = emf.createEntityManager();
 		Client c1 = em.find(Client.class, id);
 		Collection<Message> msgs= c1.getMessagesTo();
 		ArrayList<Photo> photos= new ArrayList<Photo>();
 		
 		for(Message m:msgs)
 		{
 			if(m.getId_photo()!=null && (!m.getIs_private() || (m.getIs_private() && isFriend)))
 				photos.add(m.getId_photo());
 		}
 		return photos;
 	}
 	
 // muda o id da foto de perfil para idPhoto do cliente com id =id
 	@Override
 	public boolean setProfilePhoto(int id, String password, int idPhoto) {
 		EntityManagerFactory emf = Persistence.createEntityManagerFactory("PhasebookServer");
 		EntityManager em = emf.createEntityManager();
 		Client c1 = em.find(Client.class, id);
 		if(!c1.getPassword().equals(password))
 			return false;
 		Photo p1=em.find(Photo.class, idPhoto);
 		if(p1!=null)
 			c1.setPhoto(p1);
 		else
 			return false;
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
 	public String getPhotoById(int idPhoto) {
 		EntityManagerFactory emf = Persistence.createEntityManagerFactory("PhasebookServer");
 		EntityManager em = emf.createEntityManager();
 		Photo p=em.find(Photo.class, idPhoto);
 		if(p!=null)
 			return p.getPath();
 		return "";
 	}
 	
}
