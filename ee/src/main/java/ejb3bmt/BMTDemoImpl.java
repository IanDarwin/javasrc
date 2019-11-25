package ejb3bmt;

import javax.annotation.Resource;
import javax.ejb.EJBException;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;

/**
 * There is no good reason for this to be BMT except
 * to show that its use from a CMT bean works.
 */
@Stateless
@TransactionManagement(TransactionManagementType.BEAN)
public class BMTDemoImpl implements BMTDemo {

	@Resource UserTransaction tx;
	
	@PersistenceContext(unitName="ex101_solution")
	private EntityManager em;
	
	public void loggit(String s) {
		try {
			tx.begin();
			LogEntry logEntry = new LogEntry(s);
			em.persist(logEntry);
			System.out.println("Logged: " + s);
			tx.commit();
		} catch (Exception ex) {
			try {
				tx.rollback();
			} catch (SystemException e) {
				throw new EJBException("ROLLBACK FAILED!", e);
			}
		}
	}

}
