package gui_swt;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

public class SWTUtil {

	/**
	 * Since SWT seems to want every application to provide 
	 * a (usually identical) main loop, I've extracted it to here.
	 * @param d The Display
	 * @param s The Application's main shell.
	 */
	public static void mainEventLoop(Display d, Shell s) {
		while (!s.isDisposed()) {
			if(!d.readAndDispatch()){
				d.sleep();
			}
		}
	}

}
