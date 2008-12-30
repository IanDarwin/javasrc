package ejb2.hello;

import java.rmi.RemoteException;

import javax.ejb.EJBObject;

public interface Hello extends EJBObject {
	public String sayHello(String name) throws RemoteException;
}
