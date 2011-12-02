package eai;
import java.util.List;

import javax.ejb.Remote;

import data.Photo;

@Remote
public interface PhotoBeanRemote {
	public List<Photo> getPhotos(int id,boolean isFriend);
	public String getPhotoById(int idPhoto);
	public int addPhoto(String path);
}
