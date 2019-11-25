package ejb2.serverinfo.bean;

import javax.ejb.SessionBean;
import javax.ejb.SessionContext;
import javax.naming.Context;
import javax.naming.InitialContext;

/**
 * Implementation Class for ServInfo EJB.
 *
 * @author Ian Darwin, with help from MkEjb
 * @version $Id$
 */
public class ServInfoBean implements SessionBean {

	// fields -- none

	// implementation of main logic

	/** getEnv retrieves an object from the java:comp/env environment
	 * in the EJB container, and returns it to the client.
	 * @param eName the JNDI name the object is under; the string java:comp/env/
	 * will be prepended in the bean and should not be included.
	 */
	public Object getEnv(String eName) { 
		try {
			Context ctx = new InitialContext();
			Object o = ctx.lookup("java:comp/env/" + eName);
			return o;
		} catch (Exception ex) {
			return ex.toString();
		}
	}

	// Create Methods:

	// method implementation for Create/PostCreate
	public void ejbCreate(){
		return;
	}
	public void ejbPostCreate(){
		return;
	}

	// No Find methods for session bean

	/** reference to passed Session context */
	private SessionContext ctx;

	// method implementations imposed by SessionBean interface

	/** save the session context */
	public void setSessionContext(SessionContext x) { ctx = x; }
	public void unsetSessionContext() { ctx = null; }

	public void ejbLoad() {}	// entity
	public void ejbStore() {}	// entity
	public void ejbActivate() {}
	public void ejbPassivate() {}
	public void ejbRemove() {}
}
