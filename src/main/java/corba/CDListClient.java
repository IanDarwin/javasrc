import CDService.*;
import org.omg.CosNaming.*;
import org.omg.CORBA.*;

/** This is a simple client that just lists the CDs and tracks. */
public class CDListClient {
	public static void main(String argv[]) {
		try {
			// create and initialize the ORB
			ORB orb = ORB.init(argv, null);

			// get the root naming context
			org.omg.CORBA.Object objRef =
				orb.resolve_initial_references("NameService");
			NamingContext ncRef = NamingContextHelper.narrow(objRef);

			// resolve the Object Reference in Naming
			NameComponent nc = new NameComponent("CDList", "");
			NameComponent path[] = {nc};
			CDList cdListRef = CDListHelper.narrow(ncRef.resolve(path));

			// Now get CDs from the server and print them.
			CD aCD;
			for (int i=0; i<3; i++) {
				aCD = cdListRef.getCD(i);
				System.out.println(aCD.title);
				// Slightly unrealistic: nextTrack() should be a method
				// of the CD object, not the CDList object.
				for (int t=0; t<4; t++) {
					Track aTrack = cdListRef.nextTrack();
					System.out.println(aTrack.title);
				}
			}

		} catch (Exception e) {
			System.out.println("Error: " + e) ;
			e.printStackTrace(System.err);
		}
	}
}
