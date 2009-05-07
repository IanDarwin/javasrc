package gc;

/**
 * IRC - IsRefCount. A Garbage Collection Test Class
 * Does this Java VM/GC use reference counts? RefCounting GC's
 * normally do not remove cycles...
 */
@SuppressWarnings("unused") 
public class IRC {
	
	private static int nodeCount;
	private IRC next;	// Setting this is what we need for keeping references...

	// Constructor
	public IRC () {
		super();
		System.out.println(this + "; up to " + ++nodeCount);
	}

	// finalizer (vaguely like C++ destructor, but not guaranteed to be run)
	protected void finalize() throws Throwable {
		System.out.println("finalizing " + this + " down to " + --nodeCount);
		super.finalize();
	}

	public static void main (String arg[]) {
		IRC a = new IRC();
		IRC b = new IRC();
		IRC c = new IRC();

		a.next = b;
		b.next = a;
		c.next = null;

		a = b = c = null;

		for (int loopCount = 0; loopCount < 5; loopCount++) {
			System.out.println ("Loop Number : " + loopCount);
			System.gc();
			System.runFinalization();
		}

		System.out.println ("Bye bye now...");
	}
}
