import java.applet.*;
import java.awt.*;
import java.awt.event.*;
import netscape.javascript.*;

/** Simple demonstration of an Applet invoking JavaScript.
 * SERIOUSLY NETSCAPE DEPENDANT; requires a Netscape "javaxx.jar"
 * file in classpath to compile and a browser that makes the JSObject
 * and related classes available at runtime.
 */
public class AppletJavaScript extends Applet {
	/** Called to set up the Applet */
	public void init() {
		final TextArea ta = new TextArea();
		Button button = new Button("Launch");
		ActionListener listener = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String htmlText = ta.getText();
				JSObject topWindow = 
				JSObject.getWindow(AppletJavaScript.this);
				Object args[] = new Object[3];
				args[2] = "width=300,height=300," +
					"location=0,menubar=0,status=0,toolbar=0";
				JSObject popupWindow = 
					(JSObject)topWindow.call("open", args);
				JSObject document = (JSObject)
					popupWindow.getMember("document");
				args = new Object[] {htmlText};
				document.call("write", args);
			}
		};
		button.addActionListener(listener);
		setLayout(new BorderLayout());
		add(ta, BorderLayout.CENTER);
		add(button, BorderLayout.SOUTH);
	}
}
