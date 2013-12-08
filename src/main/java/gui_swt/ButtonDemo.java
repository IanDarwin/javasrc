package gui_swt;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

/** Simple push-button demo using SWT.
 */
public class ButtonDemo {

	/**
	 * Simple Demo Main Program
	 */
	public static void main(String[] args) {
		Display d = new Display();
		Shell s = new Shell(d);
		new ButtonDemo(d, s);
		SWTUtil.mainEventLoop(d, s);
	}

	/** Construct the GUI */
	ButtonDemo(Display d, Shell s) {

		s.setText("Button Demo");
		s.setSize(200, 150);
		final Button b = new Button(s, SWT.PUSH);
		b.setBounds(50, 40, 100, 40);
		b.setText("A Button");
		b.addSelectionListener(new SelectionListener() {

			public void widgetSelected(SelectionEvent arg0) {
				System.out.println("Thanks for pushing my button!");
			}

			public void widgetDefaultSelected(SelectionEvent arg0) {
				// nothing to do
			}
		});
		s.open();
	}
}
