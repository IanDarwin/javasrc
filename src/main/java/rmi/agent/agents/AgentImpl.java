package agents;

import java.rmi.*;
import java.io.*;

/** This is the "real" agent, which gets run remotely by the server.
 * Since it is created on the Client but exported by the Server, it
 * MUST be "Remote" but NOT "UnicastRemoteObject".
 */
public class AgentImpl implements Remote, Serializable {

	private double thresh = 100;
	
	public void setThreshold(double d) throws RemoteException {
		System.out.println("Client asked agent to set Threshold to " + d);
		thresh = d;
	}
	public double getThreshold() throws RemoteException {
		return thresh;
	}
}
