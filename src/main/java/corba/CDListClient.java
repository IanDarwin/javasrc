import CDService.*;
import org.omg.CosNaming.*;
import org.omg.CORBA.*;

import com.darwinsys.util.Debug;

/** This is a simple client that just lists the CDs and tracks. */
public class CDListClient {
	public static void main(String[] argv) {
		try {
			// create and initialize the ORB
			Debug.println("corba", "Initializing the ORB");
			ORB orb = ORB.init(argv, null);

			// get the root naming context
			Debug.println("corba", "Getting Root Naming");
			org.omg.CORBA.Object objRef =
				orb.resolve_initial_references("NameService");
			NamingContext ncRef = NamingContextHelper.narrow(objRef);

			// resolve the Object Reference in Naming
			Debug.println("corba", "Finding CDListIterator Object in Naming");
			NameComponent nc = new NameComponent("CDList", "");
			NameComponent path[] = {nc};
			CDListIterator cdListRef = CDListIteratorHelper.narrow(ncRef.resolve(path));

			// Now get CDs from the server and print them.
			Debug.println("corba", "Got CDListIterator!");
			CD aCD;
			cdListRef.first();
			while (cdListRef.hasNext()) {
				Debug.println("corba", "Getting next CD Object.");
				aCD = cdListRef.next();
				System.out.println(aCD.title());
				Track[] tracks = aCD.getTracks();
				for (int t=0; t<tracks.length; t++) {
					System.out.println(tracks[t].title);
				}
			}
		} catch (Exception e) {
			System.out.println("Error: " + e) ;
			e.printStackTrace(System.err);
		}
	}
}
