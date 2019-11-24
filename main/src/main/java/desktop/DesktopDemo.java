package desktop;
import java.awt.Desktop;

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
            desktop.browse(new java.net.URI("http://www.darwinsys.com/"));
        } else {
        	System.err.println("no browser support");
        }

	}

}
