import java.util.*;
import javax.naming.*;

/** Find an object previously made available.
 * Uses the RMI registry via the JNDI service provider; needs rmiregistry
 * @version $Id$
 */
public class T2 {

	public static void main(String[] av) throws NamingException {

		System.getProperties().put("java.naming.factory.initial",
			"com.sun.jndi.rmi.registry.RegistryContextFactory");
		System.getProperties().put("java.naming.provider.url",
			"rmi://localhost:24689");

		Context ctx = new InitialContext();

		Object o = ctx.lookup("FiddleSticks");

		TData d = (TData)o;
		System.out.println("Message: " + d.getMessage());
	}
}
