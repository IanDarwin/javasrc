package lang;

/** Show Constructors conflicting */
public class Constructors {

	/** Constructor */
	public Constructors() {
		System.out.println("In the constructor");
	}
	/** Constructor that throws */
	public Constructors(int value) {
		if (value < 0)
			throw new IllegalArgumentException("Constructors: value < 0");
	}

	/** Not a Constructor, because of void */
	public void Constructors() {	// EXPECT COMPILE ERROR some compilers
		System.out.println("In void Constructor()");
	}

	void method1() {
		for (int i = 0; i < 5; i++)
			System.out.println(i);
	}

	public static void main(String[] a) {
		Constructors l = new Constructors();
		l.method1();
		l.Constructors();
		new Constructors(-1);		// expect Exception
	}
}
