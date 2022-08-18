package oo;

/**
 * Create a singleton by creative re-use (or even abuse)
 * of the Java 5 Enum mechanism. The JVM tries to guarantee
 * the singleness of each Enum constant.
 * @author Ian Darwin
 */
public enum SingletonEnum {
	INSTANCE;
	
	/** A simple demo method */
	public String demoMethod() {
		return "demo";
	}

	// other methods protected by singleton-ness would be here...
}
