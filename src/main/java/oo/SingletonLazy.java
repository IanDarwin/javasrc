package oo;

/** An example of a Singleton implementation in Java, using lazy initialization.
 * The Singleton design pattern is described in GOF; the idea is to ensure
 * that only one instance of the class will exist in a given application.
 * @author Ian F. Darwin, http://www.darwinsys.com/
 */
public class SingletonLazy {

	private static SingletonLazy instance;

	/** A private Constructor prevents any other class from instantiating. */
	private SingletonLazy() {
		// nothing to do this time
	}
	
	/** Static 'instance' method, replete with lazy construction. Having it
	 * all synchronized is the best way to make it thread safe; see the older paper
	 * "The "Double-Checked Locking is Broken" Declaration" signed by the likes
	 * of Josh Bloch, Doug Lea, Bill Pugh, and others, online at 
	 * http://www.cs.umd.edu/~pugh/java/memoryModel/DoubleCheckedLocking.html
	 */
	public synchronized static SingletonLazy getInstance() {
		if (instance == null) {
			instance = new SingletonLazy();
		}
		return instance;
	}

	/** A simple demo method */
	public String demoMethod() {
		return "demo";
	}

	// other methods protected by singleton-ness would be here...
}
