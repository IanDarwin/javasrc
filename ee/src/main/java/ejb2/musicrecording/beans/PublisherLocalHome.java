package ejb2.musicrecording.beans;

import java.util.Collection;

import javax.ejb.EJBLocalHome;
import javax.ejb.FinderException;

/** Local Home interface for the Publisher Entity EJB.
 * IMMUTABLE LIST; no create methods.
 * @author Ian Darwin
 */
public interface PublisherLocalHome extends EJBLocalHome {

	/** Find by pkey */
	public PublisherLocal findByPrimaryKey(Integer who)
		throws FinderException;

	/** Find by name */
	public PublisherLocal findByName(String who)
		throws FinderException;

	/** Get list of all (likely to be only a few dozen) */
	public Collection<PublisherLocal> findAllPublishers()
		throws FinderException;
}
