/** Demonstration of cloning. */
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

	public static void main(String[] args) { 
		Clone1 c = new Clone1();
		c.x = 100;
		c.y = 200;
		Object d = c.clone();
		System.out.println("c=" + c);
		System.out.println("d=" + d);
	}

	/** Display the current object as a string */
	public String toString() {
		return "Clone1[" + x + "," + y + "]";
	}
}
