// MemberLocalHome.java - LocalHome interface
import javax.ejb.*;
public interface MemberLocalHome extends EJBLocalHome {
	public MemberLocal findByPrimaryKey(int id)
	throws FinderException;
}
