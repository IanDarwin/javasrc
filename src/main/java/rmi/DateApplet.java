package darwinsys;

import java.awt.*;
import java.applet.*;
import java.rmi.*;
import java.util.*;

public class DateApplet extends Applet {
	Date today = null;
	RemoteDate netConn = null;

	public void init() {
		try {
			netConn = (RemoteDate)Naming.lookup("//" +
				getCodeBase().getHost() + "/DateServer");
		} catch (Exception e) {
			System.err.println("RemoteDate exception: " + e.getMessage());
			e.printStackTrace();
		}
	}

	public void paint(Graphics g) {
		try {
			if (netConn != null)
				today = netConn.getRemoteDate();
		} catch (Exception e) {
			System.err.println("RemoteDate exception: " + e.getMessage());
			e.printStackTrace();
		}
		if (netConn == null || today == null)
			showStatus("RemoteDate failed, see Java Console");
		else
			g.drawString(today.toString(), 25, 50); // use a DateFormat...
	}
}
