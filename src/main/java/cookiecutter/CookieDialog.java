import java.awt.*;
import javax.swing.*;

public class CookieDialog extends JDialog {
	Cookie undoableCookie;
	JTextField url;
	JTextField name;
	JTextField value;
	JCheckBox fromJavaScript;
	JTextField relURL;
	JCheckBox overSSL;
	JTextField expDate;

	CookieDialog(JFrame jf, String title) {
		super(jf, title, true);

		Container cp = getContentPane();
		cp.setLayout(new GridLayout(0, 2));

		cp.add(new JLabel("URL", JLabel.RIGHT));
		cp.add(url = new JTextField(20));

		cp.add(new JLabel("RelURL", JLabel.RIGHT));
		cp.add(relURL = new JTextField(20));

		cp.add(new JLabel("Name", JLabel.RIGHT));
		cp.add(name = new JTextField(20));

		cp.add(new JLabel("Value", JLabel.RIGHT));
		cp.add(value = new JTextField(20));

		cp.add(new JLabel("Expiry", JLabel.RIGHT));
		cp.add(expDate = new JTextField(20));

		cp.add(new JLabel("URL", JLabel.RIGHT));
		cp.add(url = new JTextField(20));

		cp.add(new JLabel("overSSL", JLabel.RIGHT));
		cp.add(overSSL = new JCheckBox());

		cp.add(new JLabel("fromClient", JLabel.RIGHT));
		cp.add(fromJavaScript = new JCheckBox());


		pack();
	}

	public void setCookie(Cookie c) {
		undoableCookie = c;
		url.setText(c.url);
		relURL.setText(c.relURL);
		name.setText(c.name);
		value.setText(c.value);
	}

	public Cookie getCookie() {
		long expdt;
		try {
			expdt = Long.parseLong(expDate.getText());
		} catch (NumberFormatException ne) {
			expdt = 0;
		}
		return new Cookie(
			url.getText(),
			fromJavaScript.isSelected(),
			name.getText(),
			overSSL.isSelected(),
			expdt,
			value.getText(),
			relURL.getText()
		);
	}

	public static void main(String[] args) {
		CookieDialog cd = new CookieDialog(null, "Test");
		System.out.println("Setting Visible");
		cd.setVisible(true);
		System.out.println("Dialog done, cookie = " + cd.getCookie());
	}
}
