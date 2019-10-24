package lang;

// package x;

/**
 * Check what "protected" really means: can we see into a proteced
 * attribute of another class in the same package as us?
 */
public class Protected {
	public static void main(String[] argv) {
		System.out.println("Protected values demo");
		Protected p = new Protected();
		p.process();
	}
	void process() {
		Protected2 p2 = new Protected2();	// can we spy into this?
		System.out.println("Process: " + p2.dat);
		p2.foo();
	}
}
