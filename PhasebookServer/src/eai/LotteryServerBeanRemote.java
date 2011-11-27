package eai;
import javax.ejb.Remote;

@Remote
public interface LotteryServerBeanRemote {
//	public void newDraw(LotteryBeanRemote cb);
	public void newDraw();
}
