package desktop;

import java.awt.Desktop;
import java.net.URI;
import java.io.File;

public class DesktopDemo {

	public static void main(String[] args) throws Exception {
		Desktop desktop = null;
		if (Desktop.isDesktopSupported()) {
			desktop = Desktop.getDesktop();
		} else {
			System.err.println("No desktop support");
			return;
		}

		if (desktop.isSupported(Desktop.Action.BROWSE)) {
			desktop.browse(new URI("https://darwinsys.com/"));
		} else {
			System.err.println("No browser support");
		}

		if (desktop.isSupported(Desktop.Action.PRINT)) {
			desktop.print(new File(System.getProperty("user.dir") + ".profile"));
		} else {
			System.err.println("No Print support");
		}

		if (desktop.isSupported(Desktop.Action.APP_HELP_VIEWER)) {
			desktop.openHelpViewer();
		} else {
			System.err.println("No Help support");
		}

	}

}
