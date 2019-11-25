package rmi.callback;

import java.rmi.Remote;
import java.rmi.RemoteException;

// tag::main[]
public interface TickerServer extends Remote {
	public static final String LOOKUP_NAME = "Ticker_Service";
	public void connect(Client d) throws RemoteException;
}
// end::main[]
