package ejb2.musicrecording.beans;

import java.util.Collection;

import javax.ejb.EJBLocalHome;
import javax.ejb.FinderException;

/** Publisher object Local Home interface.
 * IMMUTABLE; no create methods.
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
