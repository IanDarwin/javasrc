package oo;

/**
 * Create a singleton by creative re-use (or even abuse)
 * of the Java 5 Enum mechanism. The JVM tries to guarantee
 * the singleness of each Enum constant.
 * The Singleton design pattern is described in GoF; the idea is to ensure
 * that only one instance of the class will exist in a given application.
 * @author Ian Darwin
 */
//tag::main[]
public enum SingletonEnum {
	INSTANCE;
	
	/** A simple demo method */
	public String demoMethod() {
		return "demo";
	}

	// other methods protected by singleton-ness would be here...
}
// end::main[]
