package oo;

/** Demonstration of cloning. */
// BEGIN main
public class Clone1 implements Cloneable {

	/** Clone this object. Call super.clone() to do the work */
	public Object clone() {
		try {
			return super.clone();
		} catch (CloneNotSupportedException ex) {
			System.out.println("Now that's a surprise!!");
			throw new InternalError(ex.toString());
		}
	}

	int x;
	transient int y;	// will be cloned, but not serialized

	/** Display the current object as a string */
	public String toString() {
		return "Clone1[" + x + "," + y + "]";
	}
}
// END main
