package servers;

import agents.*;

import java.rmi.*;
import java.rmi.server.*;

public interface RunServer extends java.rmi.Remote {
	public static final String LOOKUP_NAME = "RunService";
	public RemoteStub connect(AgentImpl d) throws java.rmi.RemoteException;
}
