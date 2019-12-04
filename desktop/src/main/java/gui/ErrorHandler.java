package gui;

/** This class USED TO BE usable by AWT to handle exceptions.
 * System.setProperty("sun.awt.exception.handler", "ErrorHandler");
 * This usage is documented in the source code up to 1.4Beta for
 * java.awt.EventDispatchThread. This class exists in all standard
 * implementations (try "javap java.awt.EventQueueDispatchThread"), but
 * is not public so there's no javadoc.  NOTE: there is
 * a strong admonition that the interface WILL be changed in future.
 * <p>
 * In real life this could be part of your application, and can
 * do almost anything. The error handler itself does not need
 * to import awt, awt.event, swing, or anything else.
 *
 * @author	Ian Darwin
 */
public class ErrorHandler extends java.lang.Object {

	/** Default constructor must exist (I know it's the default;
	 * this is here in case somebody adds any other constructor).
	 */
	public ErrorHandler() {
		System.out.println("CONSTRUCTED");
	}

	public void handle(Throwable t) {
		System.err.println("Hey, I caught it!");
		System.err.println(t.toString());
	}
}
