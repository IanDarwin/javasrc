package signals;

/**
 * This is Ian Darwin's signal handler for Java JDK 1.x.
 * <b>COMPLETELY UNSUPPORTED; USE AT OWN RISK. TEST EARLY AND OFTEN.</b>
 * The general plan is:
 * <UL>
 * 	<LI>your main constructs a Thread subclass, but doesn't start it.
 * 	<LI>pass a ref to it into the routine sigHandler.
 * 	<LI>SigHandler arranges to catch signals and, when any signal
 * 		is caught, it calls your Thread.start()
 * </UL>
 * <P>N.B. We DO NOT KNOW if most jvm's are signal safe!
 * It works for the cases we've tested, on Sun's JVM.
 * <P>This way, we aren't trying to do anything too fancy like create a thread
 * from within the signal handler; it has only to call start.
 *
 *
 */
public class LibSignal {

	// declare native class
	public static native void setsighandler(Thread r);

	// Just call this guy...
	public static void setSignalHandler(Thread r) {

		System.out.println("LibSignal.setSignal(" + r + ") called.");

		setsighandler(r);		// Pass the Thread to our native code

		System.out.println("Signal handler set, sleeping");
	}

	// Static code blocks are executed once, when class file is loaded
	static {
		System.load("libsignal.so");
	}
}
