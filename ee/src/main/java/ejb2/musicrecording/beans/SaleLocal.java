package ejb2.musicrecording.beans;

import javax.ejb.*;

/**
 * Local business interface for the "put recordings on sale" Facade.
 * @author Ian Darwin
 */
public interface SaleLocal extends EJBLocalObject {

	/** Silly text-message interace, just for pinging the server */
	public String sayHello(String name);

	/**
	 * Put all the recordings on sale by the given percentage.
	 * Non-idempotent; at present there is a single price column;
	 * there should be a base price and a "current sale price".
	 */
	public boolean markDown(double delta);
}
