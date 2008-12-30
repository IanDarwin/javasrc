package ejb2.hello.src;

import java.rmi.RemoteException;

import javax.ejb.EJBObject;

public interface Hello extends EJBObject {
	public String sayHello(String name) throws RemoteException;
}
