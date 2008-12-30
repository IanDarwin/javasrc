package ejb2.localinterfaces;

// MemberLocalHome.java - LocalHome interface
import javax.ejb.EJBLocalHome;
import javax.ejb.FinderException;

public interface MemberLocalHome extends EJBLocalHome {
	public MemberLocal findByPrimaryKey(int id)
	throws FinderException;
}
