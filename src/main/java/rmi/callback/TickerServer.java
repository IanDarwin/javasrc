package darwinsys.callback;

import darwinsys.client.*;

import java.rmi.*;
import java.util.*;

public interface RegisterInterface extends java.rmi.Remote {
	public void register(ClientInterface d) throws java.rmi.RemoteException;
}
