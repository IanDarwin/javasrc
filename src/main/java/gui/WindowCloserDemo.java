import java.awt.Frame;
import java.awt.Label;
import com.darwinsys.swingui.WindowCloser;


/* Show an example of closing a Window.
 * @version $Id$
 */
public class WindowCloserDemo {

	/* Main method */
	public static void main(String[] argv) {
		Frame f = new Frame("Close Me");
		f.add(new Label("Try Titlebar Close", Label.CENTER));
		f.setSize(100, 100);
		f.setVisible(true);
		f.addWindowListener(new WindowCloser(f, true));
	}
}
