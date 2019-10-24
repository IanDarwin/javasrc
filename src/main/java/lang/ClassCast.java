package lang;

/** Class Casting Demo. */
public class ClassCast {
	public static void main(String[] argv) {

		CCA anA = new CCA();
		CCB aB = new CCB(1);
		CCC aC = new CCC(2,3);

		System.out.println("A = " + anA);
		System.out.println("B = " + aB);
		System.out.println("C = " + aC);

		CCA aCast = aC;
		System.out.println("aCast = " + aCast);

		CCC anotherC = (CCC) aCast;
		System.out.println("anotherC = " + anotherC);
	}
}

class CCA {
}

class CCB extends CCA {
	int one;
	CCB(int i) {
		one = i;
	}
	public String toString() {
		return "In a B object: " + one;
	}
}
class CCC extends CCB {
	int two;
	CCC(int i, int j) {
		super(i);	// does "one = i" for us.
		two = j;
	}
	public String toString() {
		return "In a C object: " + one + "," + two;
	}
}
