package jaxrpcservice;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * The interface for the service
 */
public interface Calc extends Remote {

    public int add(int arg0, int arg1) throws RemoteException;

	public int subtract(int arg0, int arg1) throws RemoteException;

	public int multiply(int arg0, int arg1) throws RemoteException;

	public int divide(int arg0, int arg1) throws RemoteException;
}
