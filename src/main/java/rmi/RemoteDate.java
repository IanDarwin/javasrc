package darwinsys;

import java.rmi.*;
import java.util.*;

public interface RemoteDate extends java.rmi.Remote {
	Date getRemoteDate() throws java.rmi.RemoteException;
}

