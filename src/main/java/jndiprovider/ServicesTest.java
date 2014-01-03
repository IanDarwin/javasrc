package jndiprovider;

import javax.naming.Binding;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;

/** Find an object previously made available.
 * Uses the RMI registry via the JNDI service provider; needs rmiregistry
 * @version $Id$
 */
public class ServicesTest {

	public static void main(String[] av) throws NamingException {

		Context ctx = new InitialContext();

		final int SMTP = 25;
		Object o = ctx.lookup("smtp");
		System.out.println("smtp port: " + o);
		if (((Integer)o).intValue() != SMTP) {
			System.err.println("ERROR: that should be " + SMTP);
		}

		try {
			ctx.lookup("djflsjfkldsj");
			System.err.println("GAHH -- lookup(junk) DID NOT THROW");
		} catch (NamingException expected) {
			System.err.println("lookup(junk) threw exception as it should");
			System.out.println(expected);
		}

		try {
			ctx.rename("smtp", "djflsjfkldsj");
			System.err.println("GAHH -- RENAME DID NOT THROW");
		} catch (NamingException expected) {
			System.err.println("rename() threw exception as it should");
			System.out.println(expected);
		}

		NamingEnumeration all = ctx.listBindings("");
		for (int i=0; i < 10 && all.hasMore(); i++) {
			Binding both = (Binding)all.next();
			System.out.println(both.getName() + " -> " + both.getObject());
		}

		ctx.close();

		System.getProperties().setProperty(Context.PROVIDER_URL, "/tmp/foo");
		try {
			ctx = new InitialContext();
			System.err.println("GAHH -- NOSUCHFILE DID NOT THROW");
		} catch (Exception expected) {
			System.out.println("NoSuchFile threw exception as it should");
			System.out.println(expected);
		}
	}
}
