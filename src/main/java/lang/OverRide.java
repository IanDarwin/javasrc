/** Demonstration of overriding, from Chapter 7. */
public class OverRide {
	public static void main(String av[]) {
		System.out.println("Calling an A's version of xyz");
		new A().xyz();
		System.out.println("Calling a B's version of xyz");
		new B().xyz();
	}
}

class A {
	void xyz() {
		System.out.println("In A.xyz");
	}
}

class B extends A {
	void xyz() {
		System.out.println("In B.xyz");
		super.xyz();
	}
}
