package desktop;

import java.awt.Desktop;
import java.net.URI;

public class DesktopDemo {

	public static void main(String[] args) throws Exception {
		Desktop desktop = null;
        if (Desktop.isDesktopSupported()) {
            desktop = Desktop.getDesktop();
        } else {
            System.err.println("no desktop support");
            return;
        }

        if (desktop.isSupported(Desktop.Action.BROWSE)) {
            desktop.browse(new URI("https://darwinsys.com/"));
        } else {
        	System.err.println("no browser support");
        }

	}

}
