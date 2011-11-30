package eai;
import java.util.Date;

import javax.ejb.Remote;

import data.Bet;
import data.Lottery;

@Remote
public interface LottBeanRemote {
	
	public Lottery getCurrentLottery();
	public int updateResult(int number);
	public void checkWinners(int idL);
	public boolean createNextLottery(Date nextDraw);
}
