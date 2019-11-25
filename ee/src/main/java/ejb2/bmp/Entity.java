package ejb2.bmp;

import java.rmi.RemoteException;

import javax.ejb.EJBObject;

public interface Entity extends EJBObject {

	public Recording getRecording() throws RemoteException;

}
