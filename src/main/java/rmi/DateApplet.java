package rmi;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JOptionPane;
import java.rmi.Naming;
import java.time.LocalDateTime;

// tag::main[]
public class DateApplet extends JPanel {
	private static final long serialVersionUID = 287892791;

	RemoteDate netConn = null;
	JButton b;
	JLabel statusLabel;

	public DateApplet() {
		try {
			netConn = (RemoteDate)Naming.lookup(RemoteDate.LOOKUPNAME);
		} catch (Exception e) {
			System.err.println("RemoteDate exception: " + e.getMessage());
			throw new ExceptionInInitializerError("Can't lookup " + RemoteDate.LOOKUPNAME + "; " + e);
		}
		add(b = new JButton("Get Date"));
		b.addActionListener(e -> {
				if (netConn == null) {
					showStatus("Connection failed, bye");
					return;
				}
				try {
					LocalDateTime today = netConn.getRemoteDate();
					showStatus(today.toString()); // XX use a DateFormat...
				} catch (Exception ex) {
					System.err.println("RemoteDate exception: " + ex.getMessage());
					showStatus("RemoteDate failed, see Java Console");
				}
		});
	}

	void showStatus(String s) {
		JOptionPane.showMessageDialog(null, s);
	}
}
// end::main[]
