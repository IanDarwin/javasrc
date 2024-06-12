package desktop;

import java.awt.*;

public class BrowserCheck {

	final static String urlStr = "https://darwinsys.com/";

	public void main() throws Exception {
		Desktop desktop;

		System.out.println("SysProp java.awt.headless: " + System.getProperty("java.awt.headless"));
		System.out.println("isHeadless?" + GraphicsEnvironment.isHeadless());

		// var tk = PlatformGraphicsInfo.createToolkit(); // needs -D open &c
		var tk = Toolkit.getDefaultToolkit();	// Easier, gives same result
		System.out.println("X Toolkit: " + tk);

		if (Desktop.isDesktopSupported()) {
			desktop = Desktop.getDesktop();
			System.out.println("Desktop is Supported, as " + desktop);
		} else {
			System.out.println("no desktop support");
			return;
		}

		if (desktop.isSupported(Desktop.Action.BROWSE)) {
				try {
					desktop.browse(new java.net.URI(urlStr));
					System.out.println("Check your browser to see if browser support works!");
				} catch (Exception ex) {
					System.out.println("Desktop.browse() failed: " + ex);
				}
		} else {
				System.out.println("Desktop claims not to support browse(); can't view " + urlStr);
		} 
	}
}
