package ejb2;

import java.util.Collection;
import java.util.Iterator;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

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
		RecordingHome h = (RecordingHome) ctx.lookup("RecordingHome");
		Collection<Recording> c = h.findAllByCategory("jazz");

		Iterator it = c.iterator();
		while (it.hasNext()) {
			Recording b = (Recording)it.next();
			//
			// can now call methods in this Recording
		}
	}

	// This is junk, just to make the main class compile
	interface RecordingHome {
		public Collection findAllByCategory(String cat);
	}

	// This is junk, just to make the main class compile
	class Recording {
	}
}
