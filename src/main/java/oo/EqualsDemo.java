package oo;

public class EqualsDemo {
	private int int1;
	private SomeClass obj1;

	/** Constructor */
	public EqualsDemo(int i, SomeClass o) {
		int1 = i;
		if (o == null) {
			throw new IllegalArgumentException("Object may not be null");
		}
		obj1 = o;
	}

	/** Default Constructor */
	public EqualsDemo() {
		this(0, new SomeClass());
	}

	/** Demonstration "equals" method */
	public boolean equals(Object o) {
		if (o == this)		// optimization
			return true;

		// Castable to this class? (false if == null)
		if (!(o instanceof EqualsDemo))
			return false;

		EqualsDemo other = (EqualsDemo)o;	// OK, cast to this class

		// compare field-by-field
		if (int1 != other.int1)			// compare primitives directly
			return false;
		if (!obj1.equals(other.obj1))	// compare objects using their equals
			return false;
		return true;
	}
}
