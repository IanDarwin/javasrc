import java.text.*;
/**
 * Show real values.
 * @author Ian F. Darwin, ian@darwinsys.com
 * @version $Id$
 */
public class RealValues {
	// Whether strictfp is commented out or not, it seems to
	// give the same answers, 0.3 and 0.8999999...
	public static /*strictfp*/ void main(String[] argv) {
		System.out.println("The real 0.3 is " + 0.3);
		System.out.println("The real 0.3 times 3 is " + 0.3*3);
	}
}
