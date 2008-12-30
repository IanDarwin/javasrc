package ejb2.musicrecording.beans;

import java.util.Collection;

import javax.ejb.CreateException;
import javax.ejb.EJBLocalHome;
import javax.ejb.FinderException;

public interface MusicLocalHome extends EJBLocalHome {

	public MusicLocal create(String artist_name, String title,
		int cat, double price)
		throws CreateException;

	/** Find by pkey, required method.
	 */
	public MusicLocal findByPrimaryKey(Integer primaryKey)
		throws FinderException;

	/**
	 * Finds all beans with a given category.
	 */
	public Collection findInCategory(String catagory)
		throws FinderException;

	/**
	 * Finds all the EJB greater than certain price
	 */
	public Collection findAllGreaterThan(double price) 
		throws FinderException;

	/**
	 * Finds all the EJB less than certain price
	 */
	public Collection findAllLessThan(double price) 
		throws FinderException;
}
