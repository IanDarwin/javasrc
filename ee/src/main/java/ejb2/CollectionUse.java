package ejb2;

import java.util.Collection;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

/**
 * Partial code to show using an Entity Bean's Collection-returning methods
 *
 * This program can <b>not</b> be run; it just shows the code for one part
 * of the EJB-use puzzle.
 *
 * @author Ian Darwin, https://darwinsys.com/
 * @version $Id$
 */
public class CollectionUse {

	public static void main(String[] args) throws NamingException {
		Context ctx = new InitialContext();
		RecordingHome h = (RecordingHome) ctx.lookup("RecordingHome");
		Collection<Recording> c = h.findAllByCategory("jazz");

		for (Recording b : c) {
			//
			// can now call methods in this Recording
			System.out.println(b);
		}
	}

	// This is junk, just to make the main class compile
	interface RecordingHome {
		public Collection<Recording> findAllByCategory(String cat);
	}

	// This is junk, just to make the main class compile
	class Recording {
	}
}
