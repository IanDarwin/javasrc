import CDService.*;
import org.omg.CosNaming.*;
import org.omg.CosNaming.NamingContextPackage.*;
import org.omg.CORBA.*;

/** 
 * Main class of the CDList service "server" program.
 * Unlike large parts of any CORBA App, this class was written by hand.
 * @author Ian Darwin, ian@darwinsys.com
 * @version $Id$
 */
public class CDListServer {
	protected CD myCDList[] = {
		new CD(100, "Gelatin Joe"),
		new CD(102, "Cellaphane Sylph"),
		new CD(102, "JoJo Jones and the Pedantix"),
	};

	class CDListServant extends _CDListImplBase {
		private int cdNum = 0;
		private int trkNum;
		public CD nextCD() {
			if (cdNum>=(myCDList.length-1))
				return null;
			trkNum = 0;
			return myCDList[cdNum++];
		}
		public Track  nextTrack() {
			if (trkNum++ > 4)
				return null;
			return new Track("Hippocamelephantocamelus", (short)120);
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
			ORB orb = ORB.init(argv, null);

			// create servant and register it with the ORB
			CDListServant cdListRef = new CDListServant();
			orb.connect(cdListRef);

			// get the root naming context
			org.omg.CORBA.Object objRef = 
				orb.resolve_initial_references("NameService");
			NamingContext ncRef = NamingContextHelper.narrow(objRef);

			// bind the Object Reference in Naming
			NameComponent nc = new NameComponent("CDList", "");
			NameComponent path[] = {nc};
			ncRef.rebind(path, cdListRef);

			// Wait for clients to invoke us. This use of Object
			// (fully-qualified because of ORG.OMG.CORBA.Object)
			// is equivalent to a UNIX pause() system call, that is,
			// an indefinite wait.
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
