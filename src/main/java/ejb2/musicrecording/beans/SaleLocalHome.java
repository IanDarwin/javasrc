package ejb2.musicrecording.beans;

import javax.ejb.*;

/**
 * Local Home interface for the "put recordings on sale" Facade.
 * @author Ian Darwin
 */
public interface SaleLocalHome extends EJBLocalHome {

	/** Create the session facade */
	public SaleLocal create() throws CreateException;
}
