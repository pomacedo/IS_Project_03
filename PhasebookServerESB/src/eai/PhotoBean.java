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
 	
 	// retorna uma lista de fotos publicas e privadas(consuante a amizade) respectivas ao user com id x
 	@Override
 	public List<Photo> getPhotos(int id, boolean isFriend) {
 		
 		//TODO este metodo precisa receber as msgs para poder ir buscar as fotos
 		EntityManagerFactory emf = Persistence.createEntityManagerFactory("PhasebookServerESB");
 		EntityManager em = emf.createEntityManager();
 		List<Message> msgs=(List<Message>)em.createQuery("SELECT m FROM Message m WHERE m.id_client_to LIKE ?1").setParameter(1, id).getResultList();
 		ArrayList<Photo> photos= new ArrayList<Photo>();
 		
 		for(Message m:msgs)
 		{
 			if(m.getId_photo()!=-1 && (!m.getIs_private() || (m.getIs_private() && isFriend))){
 				Photo photo=(Photo)em.createQuery("SELECT p FROM Photo p WHERE p.id LIKE ?1").setParameter(1, m.getId_photo()).getSingleResult();
 				photos.add(photo);
 			}
 		}
 		return photos;
 	}

 	@Override
 	public String getPhotoById(int idPhoto) {
 		EntityManagerFactory emf = Persistence.createEntityManagerFactory("PhasebookServerESB");
 		EntityManager em = emf.createEntityManager();
 		Photo p=em.find(Photo.class, idPhoto);
 		if(p!=null)
 			return p.getPath();
 		return "";
 	}

	@Override
	public int addPhoto(String path) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("PhasebookServerESB");
		EntityManager em = emf.createEntityManager();             

		
		Photo p=new Photo(path); 
		EntityTransaction tx=em.getTransaction();
		try{   	   
			tx.begin();
			em.persist(p);
			tx.commit();
		}catch(RollbackException ex){
			em.close();
			return -1;
		}
		Photo photo=(Photo)em.createQuery("SELECT p FROM Photo p WHERE p.path LIKE ?1").setParameter(1, path).getSingleResult();
		em.close();
		return photo.getId();
	}
 	
}
