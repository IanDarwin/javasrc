import javax.naming.*;
import javax.rmi.*;
import java.util.*;

/**
 * Partial code to show using an Entity Bean's Collection-returning methods
 *
 * This program can <b>not</b> be run; it just shows the code for one part
 * of the EJB-use puzzle.
 *
 * @author Ian Darwin, http://www.darwinsys.com/
 * @version $Id$
 */
public class CollectionUse {
	public static void main(String[] args) throws NamingException {
		Context ctx = new InitialContext();
		MyBeanHome h = (MyBeanHome) ctx.lookup("MyBeanHome");
		Collection c = h.findAllByCategory("jazz");

		Iterator it = c.iterator();
		while (it.hasNext()) {
			MyBean b = (MyBean)it.next();
			//
			// can now call methods in this MyBean
		}
	}

	// This is junk, just to make the main class compile
	interface MyBeanHome {
		public Collection findAllByCategory(String cat);
	}

	// This is junk, just to make the main class compile
	class MyBean {
	}
}
