/** Degenerate version of Iterator interface, to allow
 * JDK1.1-class compilers to compile Java 2 code.
 * It won't WORK, but it should compile.
 */

public interface Iterator {
	public boolean hasNext();
	public Object next();
	public void remove();
}
/** Degenerate decl of UnsupportedOperationException to let
 * Iterators compile.
 */
class UnsupportedOperationException extends RuntimeException {
	UnsupportedOperationException (String msg) {
		super(msg);
	}
}
