package eai;
import java.util.List;

import javax.ejb.Remote;

import data.Bet;
import data.Lottery;

@Remote
public interface BetBeanRemote {

	public boolean makeNewBet(int id, String password, int idBet, int number);
	public List<Bet> getMyCurrentBets(int id, String password, Lottery curLot);
	public List<Bet> getMyOldBets(int id,String password,Lottery curLot);
}
