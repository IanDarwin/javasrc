package ca.tcp.utils;

/**
 * Top-level class for Enumerations implementing Bloch's Typesafe Enum pattern,
 * similar to how he extended it for Java 1.5 (with valueOf() method).
 * When we move to 1.5, change all subclasses of this to J2SE 1.5 enums.
 * See Java Cookbook, 2nd Edition, Chapter 8.
 */
public abstract class Enum {
	/** The name of this class, should be set in a static initializer. */
	protected static String className = "(class not set!)";
	/** The value of this instance */
	private String value;
	/** The maximum number of values an enum can have */
	private static final int MAX_VALUES = 10;
	
	/** Although this is public, the implementing subclass' constructor must be 
	 * private to ensure typesafe enumeration pattern.
	 */
	public Enum(String val) {
		value = val;
		all[allIndex++] = this;
	}
	
	/** Returns the value of this value as a String */
	public String toString() {
		return value;
	}
	
	private final static Enum[] all = new Enum[MAX_VALUES];
	private static int allIndex;
	
	/** Returns the given Enum instance for the given String.
	 * @throws IllegalArgumentException if the input is not one of the valid values.
	 */
	public static Enum getValueOf(String s) {
		for (int i = 0; i < allIndex; i++) {
			if (all[i].value.equals(s))	{
				return all[i];
			}
		}
		throw new IllegalArgumentException("Value '" + s + "' is not a valid " + className + " enumeration value.");
	}

	public static Enum[] values() {
		return (Enum[])all.clone();
	}
}
