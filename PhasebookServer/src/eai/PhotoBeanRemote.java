package eai;
import java.util.List;

import javax.ejb.Remote;

import data.Photo;

@Remote
public interface PhotoBeanRemote {
	
	public int privNumberOfPhotos(int id);
	public int pubNumberOfPhotos(int id);
	public List<Photo> getPhotos(int id,boolean isFriend);
	public boolean setProfilePhoto(int id,String password,int idPhoto);
	public String getPhotoById(int idPhoto);
}
