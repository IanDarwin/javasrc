package lang;

// package x;

/** This class is in the same package as Protected above.
 * If this compiles, it means that "protected" DOES allow access to
 * other classes in the same package (and it does, on JDK1.1.x).
 * Note that JDK1.0 had "private protected" for this purpose, but
 * JDK1.1 does not.
 */
class Protected2 {
	/** A protected datum: can we see it from Protected1? */
	protected int dat = 42;	
	/** A protected method: can we call it from Protected1? */
	protected void foo() {
		System.out.println("In a Protected2 object's foo() method.");
	}
}
