import java.util.*;
import javax.naming.*;

/** Find an object previously made available.
 * Uses the RMI registry via the JNDI service provider; needs rmiregistry
 * @version $Id$
 */
public class JNDILookup {

	public static void main(String[] av) throws NamingException {

		System.getProperties().put("java.naming.factory.initial",
			"com.sun.jndi.rmi.registry.RegistryContextFactory");
		System.getProperties().put("java.naming.provider.url",
			"rmi://localhost/");

		Context ctx = new InitialContext();

		Object o = ctx.lookup("FiddleSticks");

		JNDIData d = (JNDIData)o;
		System.out.println("Message: " + d.getMessage());
	}
}
