import javax.ejb.*;

public class Bean implements SessionBean, MyMethods {

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
