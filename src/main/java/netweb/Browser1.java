import java.awt.*;
import java.net.*;
import java.io.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.text.*;
import javax.swing.text.html.*;

/**
 * Browser1 - Get the contents of a URL, write to stdout
 */
public class Browser1 {
	public static void main(String[] av) {
		new Browser1(av);
	}

	JEditorPane p;

	Browser1(String av[]) {
		String loc = null;
		String data = null;
		switch(av.length) {
			case 0: loc = "http://localhost/"; break;
			case 1: loc = av[0]; break;
			default:
				System.err.println("Usage: getFromURL [url]");
				System.exit(1);
		}
		try {
			URL Web = new URL(loc);
			p = new JEditorPane(Web);
			JFrame jf = new JFrame("HTML");
			p.setContentType("text/html");
			p.addHyperlinkListener(new Hyperactive());
			p.setEditable(false);
			Container cp = jf.getContentPane();
			cp.add(BorderLayout.NORTH, p);
			jf.pack();
			jf.setVisible(true);
		} catch (MalformedURLException e) {
			System.out.println("MalformedURLException: " + e);
		} catch (IOException e) {
			System.out.println("IOException: " + e);
		}
		if (data != null)
			System.out.println("Data " + data);
	}
	class Hyperactive implements HyperlinkListener {

		public void hyperlinkUpdate(HyperlinkEvent e) {
			if (e.getEventType() == HyperlinkEvent.EventType.ACTIVATED) {
				JEditorPane pane = (JEditorPane) e.getSource();
				if (e instanceof HTMLFrameHyperlinkEvent) {
					HTMLFrameHyperlinkEvent  evt = (HTMLFrameHyperlinkEvent)e;
					HTMLDocument doc = (HTMLDocument)pane.getDocument();
					doc.processHTMLFrameHyperlinkEvent(evt);
				} else {
					try {
						pane.setPage(e.getURL());
					} catch (Throwable t) {
						t.printStackTrace();
					}
				}
			}
		}
	}
}
