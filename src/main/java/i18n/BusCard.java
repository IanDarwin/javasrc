package i18n;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

import com.darwinsys.swingui.I18N;

/** Display your business-card information in a Java window.
 *
 * This is a first attempt. The next version should use a GridBagLayout.
 * @author Ian F. Darwin
 */
// BEGIN main
public class BusCard extends JFrame {

	private static final long serialVersionUID = 1L;
	private JLabel nameTF;
	private JComboBox<String> jobChoice;
	private JButton B1, B2, B3, B4;

	/** "main program" method - construct and show */
	public static void main(String[] av) {
		// create a BusCard object, tell it to show up
		new BusCard().setVisible(true);
	}

	/** Construct the object including its GUI */
	public BusCard() {

		Container cp = getContentPane();

		cp.setLayout(new GridLayout(0, 1));

		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				setVisible(false);
				dispose();
				System.exit(0);
			}
		});

		JMenuBar mb = new JMenuBar();
		setJMenuBar(mb);

		ResourceBundle b = ResourceBundle.getBundle("i18n.BusCard");

		JMenu aMenu;
		aMenu = I18N.mkMenu(b, "filemenu");
		mb.add(aMenu);
		JMenuItem mi = I18N.mkMenuItem(b, "filemenu", "exit");
		aMenu.add(mi);
		mi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		aMenu = I18N.mkMenu(b, "editmenu");
		mb.add(aMenu);
		aMenu = I18N.mkMenu(b, "viewmenu");
		mb.add(aMenu);
		aMenu = I18N.mkMenu(b, "optionsmenu");
		mb.add(aMenu);
		aMenu = I18N.mkMenu(b, "helpmenu");
		mb.add(aMenu);
		//mb.setHelpMenu(aMenu);		// needed for portability (Motif, etc.).

		setTitle(I18N.getString(b, "card"+".company", "TITLE"));

		JPanel p1 = new JPanel();
		p1.setLayout(new GridLayout(0, 1, 50, 10));

		nameTF = new JLabel("My Name", JLabel.CENTER);
		nameTF.setFont(new Font("helvetica", Font.BOLD, 18));
		nameTF.setText(I18N.getString(b, "card"+".myname", "MYNAME"));
		p1.add(nameTF);

		jobChoice = new JComboBox<>();
		jobChoice.setFont(new Font("helvetica", Font.BOLD, 14));

		// Get Job Titles from the Properties file loaded into "b"!
		String next;
		int i=1;
		do {
			next = I18N.getString(b, "job_title" + i++, null);
			if (next != null)
				jobChoice.addItem(next);
		} while (next != null);
		p1.add(jobChoice);

		cp.add(p1);

		JPanel p2 = new JPanel();
		p2.setLayout(new GridLayout(2, 2, 10, 10));

		B1 = new JButton();
		B1.setText(I18N.getString(b, "button1.label", "BUTTON LABEL"));
		p2.add(B1);

		B2 = new JButton();
		B2.setText(I18N.getString(b, "button2.label", "BUTTON LABEL"));
		p2.add(B2);

		B3 = new JButton();
		B3.setText(I18N.getString(b, "button3.label", "BUTTON LABEL"));
		p2.add(B3);

		B4 = new JButton();
		B4.setText(I18N.getString(b, "button4.label", "BUTTON LABEL"));
		p2.add(B4);
		cp.add(p2);

		pack();
	}
}
// END main
