import java.util.*;
import javax.naming.*;

/** Make an object available.
 * Uses the RMI registry via the JNDI service provider; needs rmiregistry
 * @version $Id$
 */
public class T1 {

	public static void main(String[] av) throws NamingException {
		TData d = new TData();
		d.setMessage("Qwerty Uiop!");

		System.getProperties().put("java.naming.factory.initial",
		 	"com.sun.jndi.rmi.registry.RegistryContextFactory");
		System.getProperties().put("java.naming.provider.url",
		 	"rmi://localhost:24689");

		Context ctx = new InitialContext();

		ctx.rebind("FiddleSticks", d);
	}
}
