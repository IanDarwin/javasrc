package jndi;

import java.rmi.RMISecurityManager;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;

/** List the "java:comp" of an EJB server.
 */
public class ListJavaComp {

	public static void main(String[] argv) throws NamingException {

		try {
		System.setSecurityManager(new RMISecurityManager());

		// Properties from "jndi.properites" (in . or CLASSPATH)
		Context ctx = new InitialContext();

		System.out.println("InitialContext name: " +
			ctx.getNameInNamespace());
		System.out.println("List of top-level bindings");
		NamingEnumeration o = ctx.listBindings("");

		while (o.hasMore()) {
			System.out.println("Binding: " + o.next());
		}

		String toList = argv.length == 0 ? "java:comp" : argv[0];
		System.out.println("List of bindings for " + toList);

		// Note that the list() methods give you the CLASS NAMES
		// of the things that are bound, whereas listBindings()
		// gives you the actual objects.
		NamingEnumeration n = ctx.listBindings(toList);

		while (n.hasMore()) {
			System.out.println("Binding: " + n.next());
		}

		} catch (NamingException ex) {
			System.err.println(ex);
		}
	}
}
