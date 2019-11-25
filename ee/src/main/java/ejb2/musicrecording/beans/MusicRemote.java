package ejb2.musicrecording.beans;

import java.rmi.RemoteException;

import javax.ejb.EJBObject;

/**
 * MusicRemote - Remote Business interface for MusicRecording Entity.
 * @author Rewrite by Ian Darwin
 */
public interface MusicRemote extends EJBObject {

	// A Local or Remote interface may list the abstract
	// accessors EXCEPT for the pkey, which isn't changeable

	// Or it may, as here, provide bulk data accessors.

	public MusicRecordingDO getRecording() throws RemoteException;
	public void setRecording(MusicRecordingDO rec) throws RemoteException;

	// These methods would probably be illegal
	// public void setPublisher(PublisherRemote p);
	// public PublisherRemote getPublisher();
}
