package example;

import java.util.List;

import javax.ejb.EJB;
import javax.jws.WebMethod;
import javax.jws.WebService;

import data.Photo;

import eai.PhotoBeanRemote;


@WebService(name = "photows", targetNamespace = "http://PhasebookWSs/photoWS")
public class photoWS {
	@EJB(mappedName = "PhotoBean/remote")
	PhotoBeanRemote photoBeanRem;
	
//	
//	@WebMethod
//	public int privNumberOfPhotos(String id){
//		return photoBeanRem.privNumberOfPhotos( Integer.parseInt(id));
//	}
//	@WebMethod	
//	public int pubNumberOfPhotos(String id){
//		return photoBeanRem.pubNumberOfPhotos(Integer.parseInt(id));
//	}
//	@WebMethod	
//	public List<Photo> getPhotos(String id,String isFriend){
//		return photoBeanRem.getPhotos( Integer.parseInt(id),Boolean.parseBoolean(isFriend));
//	}
//	@WebMethod	
//	public boolean setProfilePhoto(String id,String password,String idPhoto){
//		return photoBeanRem.setProfilePhoto( Integer.parseInt(id), password, Integer.parseInt(idPhoto));
//	}
//	@WebMethod	
//	public String getPhotoById(String idPhoto){
//		return photoBeanRem.getPhotoById(Integer.parseInt(idPhoto));
//	}

}
