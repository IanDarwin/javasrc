import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * Internal Frames Demo
 * @version $Id$
 */
public class JIFrameDemo {

	/* Main View */
	public static void main(String[] a) {
		final JFrame jf = new JFrame("JIFrameDemo Main Window");

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		jf.setSize(screenSize);

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
		JLabel reader = new JLabel("Mail Reader Would Be Here");
		reader.setSize(400, 300);
		mboxFrame.setContentPane(reader);
		mboxFrame.pack();
		dtp.add(mboxFrame);

		JInternalFrame compFrame = 
			new JInternalFrame("Compose Mail", true, true, true, true);
		JLabel composer = new JLabel("Mail Compose Would Be Here");
		composer.setSize(300, 200);
		compFrame.setContentPane(composer);
		compFrame.pack();
		compFrame.setLocation(50, 50);
		dtp.add(compFrame);

		JInternalFrame listFrame = 
			new JInternalFrame("Users", true, true, true, true);
		JLabel list = new JLabel("List of Users Would Be Here");
		list.setSize(500,200);
		listFrame.setContentPane(list);
		listFrame.setLocation(100, 100);
		listFrame.pack();
		dtp.add(listFrame);

		jf.setVisible(true);
        jf.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
			jf.setVisible(false);
			jf.dispose();
			System.exit(0);
			}
		});
	}
}
