package lang;

/**
 * Simple demo to show that inheritance does satisfy "implements" requirements.
 * @author ian
 */
public class InheritSatisfiesImplements {

	interface MyFace {
		public void smile();
	}

	class A {
		public void smile() {
			System.out.printf("In %s.smile()%n", getClass());
		}
	}
	
	class B extends A implements MyFace {
		
	}
	
	/**
	 * @param args
	 */
	public void runnit(String[] args) {
		System.out.println("Testing to see if an inherited method satisfies an 'implements' clause.");
		System.out.println("(if this code compiles, then it does.).");
		A a = new B();
		if (a instanceof MyFace) {
			System.out.println("(but my smiling face proves it for the doubters.)");
		} else {
			throw new IllegalStateException("Weird -- this JVM behaves differently than Sun's Java 5!");
		}
	}
	
	public static void main(String[] args) {
		new InheritSatisfiesImplements().runnit(args);
	}

}
