/** An example of a Singleton implementation in Java.
 * The Singleton design pattern is described in GOF; the idea is to ensure
 * that only one instance of the class will exist in a given application.
 * @author Ian F. Darwin, ian@darwinsys.com
 * @version $Id$
 */
public class Singleton {

	private static Singleton singleton;

	/** A private Constructor prevents any other class from instantiating. */
	private Singleton() {
		if (singleton != null) {
			throw new IllegalStateException(
				"Attempt to instantiate multiple occurences of Singleton");
		}
	}

	/** A static 'factory' method */
	public static Singleton getInstance() {
		if (singleton == null)
			singleton = new Singleton();
		return singleton;
	}

	/** Simple backdoor for SingletonTest (in this package) to 
	 * exercise the constructor, just to test its enforcement of
	 * singelton-ness. MUST NOT BE PUBLIC. Do not do even this in real code.
	 */
	static Singleton backdoor() {
		// we can do this because we're in the Singleton class
		return new Singleton();		// EXPECT EXCEPTION
	}

	// other methods protected by singleton-ness would be here...
}
