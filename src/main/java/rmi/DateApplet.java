package rmi;

package darwinsys.distdate;

import java.awt.*;
import java.awt.event.*;
import java.applet.*;
import java.rmi.*;
import java.util.*;

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
