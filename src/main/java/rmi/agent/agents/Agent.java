package rmi.agent.agents;

import java.rmi.RemoteException;

public interface Agent {
	public void setThreshold(double d) throws RemoteException;
	public double getThreshold() throws RemoteException;
}
