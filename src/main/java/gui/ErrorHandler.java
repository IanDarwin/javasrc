/** This class is usable by AWT to handle exceptions.
 * This usage is documented in the JavaDoc for 
 * java.awt.EventDispatchThread, but there is
 * an admonition that the interface WILL be changed in future.
 * <p>
 * In real life this could be part of your application, and can
 * do almost anything. The error handler itself does not need
 * to import awt, awt.event, swing, or anything else.
 *
 * @author	Ian Darwin
 * @version	$Id$
 */
public class ErrorHandler extends java.lang.Object {
	public void handle(Throwable t) {
		System.err.println("Hey, I caught it!");
		t.printStackTrace(System.err);
	}
}
