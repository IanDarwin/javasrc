package corba_rmi.javaserver;

import java.rmi.RemoteException;

import javax.rmi.PortableRemoteObject;

import corba_rmi.Demo;

public class DemoImplServer extends PortableRemoteObject implements Demo {
	
	public DemoImplServer() throws RemoteException {
		super();
	}

	public static void main(String[] args) { 
		System.out.println("This program is not finished yet");
	}

	private static int clientNum = 42;

	public String getNext() {
		return "You are client number " + ++clientNum;
	}
}
