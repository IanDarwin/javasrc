package com.darwinsys.rain;

import javax.ejb.*;
import java.util.*;
import java.rmi.*;

/** Publisher object Remote Home interface.
 */
public interface PublisherRemoteHome extends EJBHome {

	/** Create */
	public PublisherRemote create(int pkey, String pubName,
		String city, String phone) throws CreateException, RemoteException;

	/** Find by pkey */
	public PublisherRemote findByPrimaryKey(Integer who)
		throws FinderException, RemoteException;

	/** Find by name */
	public PublisherRemote findByName(String who)
		throws FinderException, RemoteException;

	/** Get list of all (likely to be only a few dozen) */
	public Collection findAllPublishers()
		throws FinderException, RemoteException;
}
