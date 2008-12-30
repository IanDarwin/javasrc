package ejb2.interfaces;

import javax.ejb.SessionBean;
import javax.ejb.SessionContext;

public class Bean implements SessionBean, MyMethods {

	private static final long serialVersionUID = 2788434211053620087L;
	
	private SessionContext sctx;

	public int computeMeaning() {
		return 42;
	}

	// Home methods

	public void ejbCreate() {
	}

	// Minimal callbacks.
	 
	public void setSessionContext(javax.ejb.SessionContext ctx) {
		sctx = ctx;
	}

	public void ejbActivate() {
	}
 
	public void ejbPassivate() {
	}
	 
	public void ejbRemove() {
	}
}
