package oo;

/** An example of a Singleton implementation in Java, using 
 * static initialization.
 * The Singleton design pattern is described in GOF; the idea is to ensure
 * that only one instance of the class will exist in a given application.
 * @author Ian F. Darwin, http://www.darwinsys.com/
 * @version $Id$
 */
public class SingletonStatic {

	private static SingletonStatic instance = new SingletonStatic();

	/** A private Constructor prevents any other class from instantiating. */
	private SingletonStatic() {
	}

	/** Static 'instance' method */
	public static SingletonStatic getInstance() {
		return instance;
	}

	// other methods protected by singleton-ness would be here...

	/** A simple demo method */
	public String demoMethod() {
		return "demo";
	}
}
