import java.util.*;
import javax.naming.*;

/** Find an object previously made available.
 * Uses the RMI registry via the JNDI service provider; needs rmiregistry
 * @version $Id$
 */
public class JNDILookup {

	public static void main(String[] av) throws NamingException {

		Context ctx = new InitialContext();

		Object o = ctx.lookup("FiddleSticks");

		JNDIData d = (JNDIData)o;
		System.out.println("Message: " + d.getMessage());
	}
}
