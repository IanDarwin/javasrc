package darwinsys.distdate;

import java.rmi.*;
import java.util.*;

public interface RemoteDate extends java.rmi.Remote {
	public Date getRemoteDate() throws java.rmi.RemoteException;
	public NonSerNonRem getRemoteNon() throws java.rmi.RemoteException;
}

