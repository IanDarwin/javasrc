package rmi.agent.servers;

import java.rmi.server.RemoteStub;

import rmi.agent.agents.AgentImpl;

public interface RunServer extends java.rmi.Remote {
	public static final String LOOKUP_NAME = "RunService";
	public RemoteStub connect(AgentImpl d) throws java.rmi.RemoteException;
}
