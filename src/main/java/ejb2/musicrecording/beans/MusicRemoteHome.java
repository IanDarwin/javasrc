package ejb2.musicrecording.beans;

import java.rmi.RemoteException;
import java.util.Collection;

import javax.ejb.CreateException;
import javax.ejb.EJBHome;
import javax.ejb.FinderException;

/**
 * This interface is the home interface for the Music bean.
 * It has a create method and many finder methods
 */
public interface MusicRemoteHome extends EJBHome {

	public MusicRemote create(String artist_name, String title,
		int cat, double price)
		throws CreateException, RemoteException;

	/** Find by pkey, required method.
	 */
	public MusicRemote findByPrimaryKey(Integer recordingID)
		throws FinderException, RemoteException;

	/**
	 * Finds all beans with a given category.
	 */
	public Collection findInCategory(String catagory)
		throws FinderException, RemoteException;

	/**
	 * Finds all the EJB greater than certain price
	 */
	public Collection findAllGreaterThan(double price) 
		throws FinderException, RemoteException;

	/**
	 * Finds all the EJB less than certain price
	 */
	public Collection findAllLessThan(double price) 
		throws FinderException, RemoteException;
}
