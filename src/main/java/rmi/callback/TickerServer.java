package com.darwinsys.callback;

import com.darwinsys.client.*;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface TickerServer extends Remote {
	public static final String LOOKUP_NAME = "Ticker_Service";
	public void connect(Client d) throws RemoteException;
}
