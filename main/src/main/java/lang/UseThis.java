package lang;

// This shows off the uses of "this". It is a non-public class
// just so the file name UseThis doesn't have to be the class name
// (some students found "This" in the class name confusing in this example).
class MyClass {
	final static int MAXX=640, MAXY=480;	// low-res (VGA)
	int x, y;				// current location
	/** Construct a MyClass with x and y values */
	MyClass(int x, int y) {
		this.x = x;
		this.y = y;
        }
	/** Construct a MyClass with default values */
	MyClass() {
		this(MAXX/2, MAXY/2);   // Use the constructor above
        }

	public String toString() {
		return "[" + x + "," + y + "]";
	}

	/** Test main program */
	public static void main(String[] av) {
		System.out.println(new MyClass(300,100));
		System.out.println(new MyClass());
	}
}
