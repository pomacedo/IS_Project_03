package eai;
import java.util.Date;

import javax.ejb.Remote;

@Remote
public interface LotteryBeanRemote {
	public void scheduleTimer(long ms);
	public Date getNextDraw();
	public int getNumber();
	public void ClearTimers();
}
