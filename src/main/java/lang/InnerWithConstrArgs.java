package lang;

/** Construct an anonymous inner class that requires superclass constructor arguments. */
public class InnerWithConstrArgs {
	public static abstract class A {
		String s;
		public abstract String getTitle();
		public A(String s) {
			this.s = s;
		}
	}
	
	// Constructing an anonymous inner class that requires superclass constructor arguments
	// is just a matter of making sure the args are visible and passing them
	// into the "new SuperclassName()" constructor call that creates and anon inner instance
	public static void main(String[] args) {
		A anA = new A("Hello") {
			@Override
			public String getTitle() {
				return s;
			}			
		};
		System.out.println(anA.getTitle());
	}
}
