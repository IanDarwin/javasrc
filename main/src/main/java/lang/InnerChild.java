package lang;

// tag::main[]
/**
 * Demonstrate an Inner Child class
 * @author Ian F. Darwin, https://darwinsys.com/
 */
public class InnerChild {
	public class InnerInnerChild extends InnerChild {
		private InnerInnerChild() {
			// empty
		}
	}
	public static void main(String[] argv) {
		// System.out.println(new InnerChild.InnerInnerChild()); // NOT how!
		InnerChild x = new InnerChild();
		System.out.println(x.new InnerInnerChild());
	}
}
// end::main[]
