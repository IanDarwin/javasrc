import java.util.*;
import javax.naming.*;

/** Make an object available.
 * Uses the RMI registry via the JNDI service provider; needs rmiregistry
 * @version $Id$
 */
public class JNDIPublish  {

	public static void main(String[] av) throws NamingException {
		JNDIData d = new JNDIData();
		d.setMessage("Qwerty Uiop!");

		Context ctx = new InitialContext();

		ctx.rebind("FiddleSticks", d);
	}
}
