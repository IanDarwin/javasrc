package rmi;

import java.awt.*;
import javax.Swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.Naming;
import java.util.Date;

// BEGIN main
public class DatePanel extends JPanel {
	
	private static final long serialVersionUID = 287892791;
	RemoteDate netConn = null;
	JButton b;
	JLabel statusLabel;

	public DatePanel() {
		try {
			netConn = (RemoteDate)Naming.lookup(RemoteDate.LOOKUPNAME);
		} catch (Exception e) {
			System.err.println("RemoteDate exception: " + e.getMessage());
			e.printStackTrace();
		}
		add(b = new Button("Get Date"));
		b.addActionListener(e -> {
				if (netConn == null) {
					showStatus("Connection failed, bye");
					return;
				}
				try {
					Date today = netConn.getRemoteDate();
					statusLabel.setText(today.toString()); // XX use a DateFormat...
				} catch (Exception ex) {
					System.err.println("RemoteDate exception: " + ex.getMessage());
					statusLabel.setText("RemoteDate failed, see Java Console");
				}
		});
		add(statusLabel = new JLabel("");
	}
}
// END main
