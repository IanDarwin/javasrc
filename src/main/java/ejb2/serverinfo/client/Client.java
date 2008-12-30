package ejb2.serverinfo.client;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.rmi.PortableRemoteObject;

import ejb2.serverinfo.bean.ServInfo;
import ejb2.serverinfo.bean.ServInfoHome;

public class Client {

	public static void main(String[] args) throws Exception {

		Context ctx = new InitialContext();

		println("Lookup h1");
		ServInfoHome h1 = (ServInfoHome)PortableRemoteObject.narrow(
			ctx.lookup("ServInfo1"), ServInfoHome.class);
		println("h1 = " + h1.getClass());

		println("Lookup h2");
		ServInfoHome h2 = (ServInfoHome)PortableRemoteObject.narrow(
			ctx.lookup("ServInfo2"), ServInfoHome.class);
		println("h2 = " + h2.getClass());

		ServInfo c1 = h1.create();

		ServInfo c2 = h2.create();

		println("c1 sayeth: " + c1.getEnv("duplicate"));
		println("c2 sayeth: " + c2.getEnv("duplicate"));
	}

	static void println(String s) { System.out.println(s); }
}
