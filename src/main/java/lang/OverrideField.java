package lang;

/** Demonstration of overriding fields. */
public class OverrideField {
	public static void main(String[] av) {
		System.out.println("OA's version of getAttr returns: " +
			new OA().getAttr());
		System.out.println("OB's version of getAttr returns: " +
			new OB().getAttr());
		// Declared as OA, instantiated as OB, so gets OB's version of things.
		OA c = new OB();
		System.out.println("C's version of getAttr returns: " +
			c.getAttr());
	}
}

class OA {
	final int attr = 1;
	int getAttr() {
		System.out.println("In OA.getAttr");
		return attr;
	}
}

class OB extends OA {
	final int attr = 2;
	int getAttr() {
		System.out.println("In OB.getAttr");
		super.getAttr(); // Just to show flow of control
		return attr;
	}
}
