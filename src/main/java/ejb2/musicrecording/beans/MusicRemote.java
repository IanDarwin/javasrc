package com.darwinsys.rain;

import java.rmi.*;
import javax.ejb.*;

/**
 * MusicRemote - Entity Bean for Music Recordings in RainForest.
 * There are no methods in the Remote interface.
 * @author Rewrite by Ian Darwin
 */
public interface MusicRemote extends EJBObject {

	// A Local or Remote interface may list the abstract
	// accessors EXCEPT for the pkey, which isn't changeable

	// Or it may, as here, provide bulk data accessors.

	public MusicRecordingDO getRecording() throws RemoteException;
	public void setRecording(MusicRecordingDO rec) throws RemoteException;

	// These methods would be illegal
	// public void setPublisher(PublisherRemote p);
	// public PublisherRemote getPublisher();
}
