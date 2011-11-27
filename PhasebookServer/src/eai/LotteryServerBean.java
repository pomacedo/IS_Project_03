package eai;

import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.RollbackException;

import data.Lottery;
/**
 * Session Bean implementation class LotteryServerBean
 */
@Stateless
public class LotteryServerBean implements LotteryServerBeanRemote {

    /**
     * Default constructor. 
     */
	
    public LotteryServerBean() {
        // TODO Auto-generated constructor stub
    }
    
    public void newDraw(){
    	InitialContext ctx;
		try {
			ctx = new InitialContext();
			
			LotteryBeanRemote cb=(LotteryBeanRemote)ctx.lookup("LotteryBean/remote");
			LottBean lb = new LottBean();
			int idL=lb.updateResult(cb.getNumber());
			if(idL!=-1)
				lb.checkWinners(idL);
			
			lb.createNextLottery((Date)cb.getNextDraw());

		} catch (NamingException e) {
			System.out.println("SERVER: connection error");
		}		
    }

}
