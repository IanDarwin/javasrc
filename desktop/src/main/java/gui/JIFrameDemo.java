package gui;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

/**
 * Internal Frames Demo
 */
public class JIFrameDemo {

	/* Main View */
	public static void main(String[] a) {
		final JFrame jf = new JFrame("JIFrameDemo Main Window");

		JMenuBar mb = new JMenuBar();
		jf.setJMenuBar(mb);
		JMenu fm = new JMenu("File");
		mb.add(fm);
		JMenuItem mi;
		fm.add(mi = new JMenuItem("Exit"));
		mi. addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});

		JDesktopPane dtp = new JDesktopPane();
		jf.setContentPane(dtp);

		JInternalFrame mboxFrame = 
			new JInternalFrame("Mail Reader", true, true, true, true);
		JLabel reader = new JLabel("Mail Reader Would Be Here",  JLabel.CENTER);
		mboxFrame.setContentPane(reader);
		mboxFrame.setSize(400, 300);
		mboxFrame.setLocation(50, 50);
		mboxFrame.setVisible(true);
		dtp.add(mboxFrame);

		JInternalFrame compFrame = 
			new JInternalFrame("Compose Mail", true, true, true, true);
		JLabel composer = new JLabel("Mail Compose Would Be Here",  JLabel.CENTER);
		compFrame.setContentPane(composer);
		compFrame.setSize(300, 200);
		compFrame.setLocation(450, 200);
		compFrame.setVisible(true);
		dtp.add(compFrame);

		JInternalFrame listFrame = 
			new JInternalFrame("Users", true, true, true, true);
		JLabel list = new JLabel("List of Users Would Be Here",  JLabel.CENTER);
		listFrame.setContentPane(list);
		listFrame.setLocation(450, 400);
		listFrame.setSize(500, 200);
		listFrame.setVisible(true);
		dtp.add(listFrame);

		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		jf.setSize(screenSize);
		jf.setLocation(20, 20);

		jf.setVisible(true);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
