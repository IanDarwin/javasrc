public class Clone1 implements Cloneable {

	/** Clone this object. Just call super.clone() to do the work */
	public Object clone() throws CloneNotSupportedException {
		return super.clone();
	}

	int x;
	transient int y;	// will be cloned, but not serialized

	public static void main(String[] args) { 
		Clone1 c = new Clone1();
		c.x = 100;
		c.y = 200;
		try {
			Object d = c.clone();
			System.out.println("c=" + c);
			System.out.println("d=" + d);
		} catch (CloneNotSupportedException ex) {
			System.out.println("Now that's a surprise!!");
			System.out.println(ex);
		}
	}

	/** Display the current object as a string */
	public String toString() {
		return "Clone1[" + x + "," + y + "]";
	}
}
