package gui_swt;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

public class ButtonDemo {

	/**
	 * Simple Demo Main Program
	 */
	public static void main(String[] args) {
		new ButtonDemo();
	}
	
	ButtonDemo() {
		Display d = new Display();
		Shell s = new Shell(d);
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
		SWTUtil.mainEventLoop(d, s);
	}
}
