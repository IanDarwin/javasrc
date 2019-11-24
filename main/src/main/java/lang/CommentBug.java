package lang;

/** The documentation, and the Java Language Spec, claim that
 * slash-star comments do not nest. But they do in some compilers!
 */
public class CommentBug {
	/* This should be a /* compile-time */ error */	// EXPECT COMPILE ERROR
	public static void main(String[] a) {
		System.out.println("SHOULD NOT HAVE COMPILED!!");
	}
}
