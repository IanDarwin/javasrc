package agents;

import java.rmi.*;

public interface Agent {
	public void setThreshold(double d) throws RemoteException;
	public double getThreshold() throws RemoteException;
}
