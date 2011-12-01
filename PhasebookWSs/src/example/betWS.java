package example;

import java.util.List;

import javax.ejb.EJB;
import javax.jws.WebMethod;
import javax.jws.WebService;
import data.Bet;
import data.Lottery;
import eai.BetBeanRemote;

@WebService(name = "betws", targetNamespace = "http://PhasebookWSs/betWS")
public class betWS {
		@EJB(mappedName = "BetBeanRemote/remote")
		BetBeanRemote betBeanRem;
		
//		
//		@WebMethod
//		public boolean makeNewBet(int id, String password, int idBet, int number)
//		{
//			return betBeanRem.makeNewBet( id,  password,  idBet,  number);
//		}
//		@WebMethod
//		public List<Bet> getMyCurrentBets(int id, String password, Lottery curLot)
//		{
//			return betBeanRem.getMyCurrentBets(id,password,curLot);
//		}
//		@WebMethod
//		public List<Bet> getMyOldBets(int id,String password,Lottery curLot){
//			return betBeanRem.getMyOldBets(id,password,curLot);
//		}
//	
	

}
