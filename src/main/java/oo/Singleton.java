package oo;

/** An example of a Singleton implementation in Java, using lazy initialization.
 * The Singleton design pattern is described in GOF; the idea is to ensure
 * that only one instance of the class will exist in a given application.
 * @author Ian F. Darwin, http://www.darwinsys.com/
 * @version $Id$
 */
public class Singleton {

	private static Singleton instance = new Singleton();

	/** A private Constructor prevents any other class from instantiating. */
	private Singleton() {
		
	}
	
	/** The Static initializer constructs the instance at class loading time */
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
