import CDService.*;
import org.omg.CosNaming.*;
import org.omg.CosNaming.NamingContextPackage.*;
import org.omg.CORBA.*;

/** 
 * Main class of the CDList service "server" program.
 * As is usual for the Client and Server, this class was written by hand,
 * unlike all the goo that is generated directly from the IDL file.
 * @author Ian Darwin, ian@darwinsys.com
 * @version $Id$
 */
public class CDListServer {
	Track[] tracks = {
		new Track("Track 1", 100),
		new Track("Track 2", 123),
	};

	protected class CDimpl extends _CDImplBase {
		String theTitle;
		CDimpl(String t) {
			title(t);
		}
		public void title(String t) {
			theTitle = t;
		}
		public String title() {
			return theTitle;
		}
		public Track[] getTracks() {
			return tracks;
		}
	}
	protected CD myCDList[] = {
		new CDimpl("Gelatin Joe"),
		new CDimpl("Cellaphane Sylph"),
		new CDimpl("JoJo Jones and the Pedantix"),
	};

	class CDListIteratorServant extends _CDListIteratorImplBase {
		private int cdNum = 0;
		public void first() {
			cdNum = 0;
		}
		public boolean hasNext() {
			return cdNum < (myCDList.length);
		}
		public CD next() {
			if (cdNum>(myCDList.length-1))
				throw new IllegalArgumentException("CD number too big");
			return myCDList[cdNum++];
		}
	}

	/** Execution of Java programs starts at main() */
	public static void main(String argv[])
	{
		new CDListServer().runServer(argv);
	}

	/** Do the work of contacting the ORB, registering with it,
	 * binding the Object, and waiting for clients. 
	 */
	protected void runServer(String[] argv) {
		try {
			// create and initialize the ORB
			Debug.println("corba", "Contacting the ORB of the apocalypse...");
			ORB orb = ORB.init(argv, null);

			// create servant and register it with the ORB
			CDListIteratorServant cdListRef = new CDListIteratorServant();
			Debug.println("corba", "Connecting cdListRef to ORB...");
			orb.connect(cdListRef);

			// get the root naming context
			Debug.println("corba", "Getting root naming context...");
			org.omg.CORBA.Object objRef = 
				orb.resolve_initial_references("NameService");
			NamingContext ncRef = NamingContextHelper.narrow(objRef);

			// bind the Object Reference in Naming
			Debug.println("corba", "Binding object reference in NameService");
			NameComponent nc = new NameComponent("CDList", "");
			NameComponent path[] = {nc};
			ncRef.rebind(path, cdListRef);

			// Wait for clients to invoke us. This use of Object
			// (fully-qualified because of ORG.OMG.CORBA.Object)
			// is equivalent to a UNIX pause() system call, that is,
			// an indefinite wait.
			System.out.println("Server: Waiting for clients...");
			java.lang.Object sync = new java.lang.Object();
			synchronized (sync) {
				sync.wait();
			}

		} catch (Exception e) {
			System.err.println("ERROR: " + e);
			e.printStackTrace(System.out);
		}
    }
}
