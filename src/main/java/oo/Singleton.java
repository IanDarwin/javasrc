package oo;

/** An example of a Singleton implementation in Java, using static initialization.
 * The Singleton design pattern is described in GOF; the idea is to ensure
 * that only one instance of the class will exist in a given application.
 * @author Ian F. Darwin, http://www.darwinsys.com/
 */
// BEGIN main
public class Singleton {

	private static Singleton instance;

	/** A private Constructor prevents any other class from instantiating. */
	private Singleton() {
		// nothing to do this time
	}
	
	/** The Static initializer constructs the instance at class loading time;
	 * this is to simulate a more involved construction process (it it
	 * were really simple, you'd just use an initializer)
	 */
	static {
		instance = new Singleton();
	}

	/** Static 'instance' method */
	public static Singleton getInstance() {
		return instance;
	}

	// other methods protected by singleton-ness would be here...

	/** A simple demo method */
	public String demoMethod() {
		return "demo";
	}
}
// END main
