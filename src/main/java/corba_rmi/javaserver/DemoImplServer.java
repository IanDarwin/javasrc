package corba_rmi.javaserver;

import corba_rmi.Demo;
import javax.naming.*;

public class DemoImplServer extends PortableRemoteObject implements Demo {
	public static void main(String[] args) { 
		System.out.println("This program is not finished yet");
	}

	private static int clientNum = 42;

	public String getNext() {
		return "You are " + ++clientNum;
	}
}
