/** Class Casting Demo. */
public class ClassCast {
	public static void main(String[] argv) {

		A anA = new A();
		B aB = new B(1);
		C aC = new C(2,3);

		System.out.println("A = " + anA);
		System.out.println("B = " + aB);
		System.out.println("C = " + aC);

		A aCast = aC;
		System.out.println("aCast = " + aCast);

		C anotherC = (C) aCast;
		System.out.println("anotherC = " + anotherC);
	}
}

class A {
}

class B extends A {
	int one;
	B(int i) {
		one = i;
	}
	public String toString() {
		return "In a B object: " + one;
	}
}
class C extends B {
	int two;
	C(int i, int j) {
		super(i);	// does "one = i" for us.
		two = j;
	}
	public String toString() {
		return "In a C object: " + one + "," + two;
	}
}
