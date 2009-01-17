package ejb2.musicrecording.beans;

import java.util.Collection;

import javax.ejb.CreateException;
import javax.ejb.EJBLocalHome;
import javax.ejb.FinderException;

/**
 * The LocalHome interface for the MusicRecording Entity EJB2.1
 * @author Ian Darwin
 */
public interface MusicLocalHome extends EJBLocalHome {

	/** Create a MusicRecording with artist, title, category and price */
	public MusicLocal create(String artist_name, String title,
		int cat, double price)
		throws CreateException;

	/** Find by pkey, required method.
	 */
	public MusicLocal findByPrimaryKey(Integer primaryKey)
		throws FinderException;

	/**
	 * Finds all Recordings, period.
	 */
	public Collection<MusicLocal> findAll()
		throws FinderException;

	/**
	 * Finds all beans with a given category.
	 */
	public Collection<MusicLocal> findByCategory(int category)
		throws FinderException;

	/**
	 * Finds all the EJB greater than certain price
	 */
	public Collection<MusicLocal> findAllGreaterThan(double price) 
		throws FinderException;

	/**
	 * Finds all the EJB less than certain price
	 */
	public Collection<MusicLocal> findByPriceLessThan(double price) 
		throws FinderException;
}
