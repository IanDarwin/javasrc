package cookiecutter;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class CookieDialog extends JDialog {
	Cookie undoableCookie;
	JTextField url;
	JTextField name;
	JTextField value;
	JCheckBox fromJavaScript;
	JTextField path;
	JCheckBox secure;
	JTextField expDate;
	JButton okButton, cancelButton;

	CookieDialog(JFrame jf, String title) {
		super(jf, title, true);

		Container cp = getContentPane();
		cp.setLayout(new GridLayout(0, 2));

		cp.add(new JLabel("Domain", JLabel.RIGHT));
		cp.add(url = new JTextField(20));

		cp.add(new JLabel("Path", JLabel.RIGHT));
		cp.add(path = new JTextField(20));

		cp.add(new JLabel("Name", JLabel.RIGHT));
		cp.add(name = new JTextField(20));

		cp.add(new JLabel("Value", JLabel.RIGHT));
		cp.add(value = new JTextField(20));

		cp.add(new JLabel("Expiry", JLabel.RIGHT));
		cp.add(expDate = new JTextField(20));

		cp.add(new JLabel("secure", JLabel.RIGHT));
		cp.add(secure = new JCheckBox());

		cp.add(new JLabel("fromClient", JLabel.RIGHT));
		cp.add(fromJavaScript = new JCheckBox());

		cp.add(okButton = new JButton("OK"));
		okButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		cp.add(cancelButton = new JButton("Cancel"));
		okButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// JOptionPane.showMessageDialog(CookieDialog.this,
					// "Cannot cancel yet", "Cannot cancel yet",
					// JOptionPane.ERROR_DIALOG);
				dispose();
			}
		});

		pack();
	}

	public void setCookie(Cookie c) {
		undoableCookie = c;
		url.setText(c.url);
		path.setText(c.path);
		name.setText(c.name);
		value.setText(c.value);
		// XXX
	}

	public Cookie getCookie() {
		int expdt;
		try {
			expdt = Integer.parseInt(expDate.getText());
		} catch (NumberFormatException ne) {
			expdt = 0;
		}
		return new Cookie(
			name.getText(),
			value.getText(),
			url.getText(),
			path.getText(),
			expdt,
			secure.isSelected(),
			fromJavaScript.isSelected()
		);
	}

	public static void main(String[] args) {
		CookieDialog cd = new CookieDialog(null, "Test");
		System.out.println("Setting Visible");
		cd.setVisible(true);
		System.out.println("Dialog done, cookie = " + cd.getCookie());
	}
}
