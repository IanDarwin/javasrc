package applet;

import java.applet.Applet;
import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import netscape.javascript.JSObject;

/** Simple demonstration of an Applet invoking JavaScript.
 * SERIOUSLY BROWSER DEPENDANT; requires a Netscape "javaxx.jar" or
 * "netscape.jar" on CLASSPATH to compile, AND a browser that makes the 
 * JSObject and related classes available at runtime.
 */
public class AppletJavaScript extends Applet {

	private static final long serialVersionUID = 1338170125138331189L;

	/** Called to set up the Applet */
	public void init() {
		final TextArea ta = new TextArea();
		Button button = new Button("Launch");
		ActionListener listener = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String htmlText = ta.getText();
					JSObject topWindow = 
						JSObject.getWindow(AppletJavaScript.this);
					Object args[] = new Object[3];
					// XXX what goes in 0 and 1 of this array?
					args[2] = "width=300,height=300," +
						"location=0,menubar=0,status=0,toolbar=0";
					JSObject popupWindow = 
						(JSObject)topWindow.call("open", args);
					JSObject document = (JSObject)
						popupWindow.getMember("document");
					args = new Object[] {htmlText};
					document.call("write", args);
				} catch (Exception ex) {
					showStatus(ex.getClass().getName() + " " + ex.getMessage());
				}
			}
		};
		button.addActionListener(listener);
		setLayout(new BorderLayout());
		add(ta, BorderLayout.CENTER);
		add(button, BorderLayout.SOUTH);
	}
}
