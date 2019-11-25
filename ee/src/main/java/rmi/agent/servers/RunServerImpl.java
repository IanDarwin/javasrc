package rmi.agent.servers;

import java.rmi.RemoteException;
import java.rmi.server.RemoteStub;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

import rmi.agent.agents.AgentImpl;

/** This is the main class of the server */
public class RunServerImpl
	extends UnicastRemoteObject
	implements RunServer
{
	private static final long serialVersionUID = -58057723022819850L;
	/** The list of Agents we've received */
	List<AgentImpl> list;

	public RunServerImpl() throws RemoteException {
		list = new ArrayList<AgentImpl>();
	}

	/** The remote method that "does all the work". This won't get
	 * called until the client starts up.
	 */
	public RemoteStub connect(AgentImpl da) throws RemoteException {
		System.out.print("Adding client " + da);
		try {
			System.out.print(" from ");
			System.out.print(getClientHost());
		} catch (Exception ex) {
			// nothing
		}
		System.out.println();
		list.add(da);

		// exportObject() returns the Stub; we return it to the client.
		return UnicastRemoteObject.exportObject(da);
	}
}
