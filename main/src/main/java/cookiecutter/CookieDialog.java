package cookiecutter;

import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class CookieDialog extends JDialog {
	/**  */
	private static final long serialVersionUID = 542126890915376229L;
	private JTextField url;
	private JTextField name;
	private JTextField value;
	private JCheckBox fromJavaScript;
	private JTextField path;
	private JCheckBox secure;
	private JTextField expDate;
	private JButton okButton, cancelButton;

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
		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});

		pack();
	}

	public void setCookie(Cookie c) {
		url.setText(c.url);
		path.setText(c.path);
		name.setText(c.name);
		value.setText(c.value);
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


}
