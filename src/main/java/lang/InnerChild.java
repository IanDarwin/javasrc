//+
/**
 * Demonstrate an Inner Child class
 * @author Ian F. Darwin, ian@darwinsys.com
 * @version $Id$
 */
public class InnerChild {
	public class InnerInnerChild extends InnerChild {
	}
	public static void main(String[] argv) {
		// System.out.println(new InnerChild.InnerInnerChild()); // NOT how!
		InnerChild x = new InnerChild();
		System.out.println(x.new InnerInnerChild());
	}
}
//-
