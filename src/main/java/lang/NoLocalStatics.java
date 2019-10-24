package lang;

/**
 * Show that you can't have static variables in a method, unlike C/C++
 */
public class NoLocalStatics {
	public static void main(String[] argv) {
		NoLocalStatics t = new NoLocalStatics();
		t.process();
	}
	void process() {
		static int a = 42;			// EXPECT COMPILE ERROR
		System.out.println("Process: " + a);
	}
}
