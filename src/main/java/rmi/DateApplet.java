package rmi;

import java.applet.Applet;
import java.awt.Button;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.Naming;
import java.util.Date;

public class DateApplet extends Applet {
	RemoteDate netConn = null;
	Button b;
	Label statusLabel;

	public void init() {
		try {
			netConn = (RemoteDate)Naming.lookup(RemoteDate.LOOKUPNAME);
		} catch (Exception e) {
			System.err.println("RemoteDate exception: " + e.getMessage());
			e.printStackTrace();
		}
		add(b = new Button("Get Date"));
		b.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				if (netConn == null) {
					showStatus("Connection failed, bye");
					return;
				}
				try {
					Date today = netConn.getRemoteDate();
					showStatus(today.toString()); // XX use a DateFormat...
				} catch (Exception ex) {
					System.err.println("RemoteDate exception: " + ex.getMessage());
					showStatus("RemoteDate failed, see Java Console");
				}
			}
		});
	}
}
