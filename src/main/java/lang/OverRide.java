package lang;

/** Demonstration of overriding, from Chapter 7. */
public class OverRide {
	public static void main(String[] av) {
		System.out.println("Calling an A's version of xyz");
		new AAA().xyz();
		System.out.println("Calling a B's version of xyz");
		new BBB().xyz();
	}
}

class AAA {
	void xyz() {
		System.out.println("In AAA.xyz");
	}
}

class BBB extends AAA {
	void xyz() {
		System.out.println("In BBB.xyz");
		super.xyz();
	}
}
