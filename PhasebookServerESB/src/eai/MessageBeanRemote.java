package eai;
import java.util.List;
import javax.ejb.Remote;

import data.Message;

@Remote
public interface MessageBeanRemote 
{
 
	public List<Message> getPosts(int idViewer,String password);
	public List<Message> getPosts(int idViewer,String password,int idTo);
	public boolean sendMsg(int id_from,String password,String message,int id_to,boolean isPrivate);
	public boolean sendMsg(int id_from,String password,String message,int id_to,boolean isPrivate,String photoPath);
	public void deleteMessage(int id,String password,int idMsg);
	public void sendEmailNotification(int id);
	public int privNumberOfPhotos(int id);
	public int pubNumberOfPhotos(int id);
}
