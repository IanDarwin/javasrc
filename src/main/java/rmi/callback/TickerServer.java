package com.darwinsys.callback;

import com.darwinsys.client.*;

import java.rmi.*;

public interface TickerServer extends java.rmi.Remote {
	public static final String LOOKUP_NAME = "Ticker Service";
	public void connect(Client d) throws java.rmi.RemoteException;
}
