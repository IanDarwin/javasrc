package netweb;

import java.awt.BorderLayout;
import java.awt.Container;
import java.io.IOException;
import java.net.URL;

import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.event.HyperlinkEvent;
import javax.swing.event.HyperlinkListener;
import javax.swing.event.HyperlinkEvent.EventType;
import javax.swing.text.html.HTMLDocument;
import javax.swing.text.html.HTMLFrameHyperlinkEvent;

/**
 * Browser1 - Get the contents of a URL, show in a window, with links.
 */
public class Browser1 {
	public static void main(String[] av) throws IOException {
		new Browser1(av);
	}

	final JEditorPane p;

	Browser1(String av[]) throws IOException {
		String loc = null;
		switch(av.length) {
			case 0: loc = "http://localhost/"; break;
			case 1: loc = av[0]; break;
			default:
				throw new IllegalArgumentException("Usage: getFromURL [url]");
		}

		URL web = new URL(loc);
		p = new JEditorPane(web);
		JFrame jf = new JFrame("HTML");
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		p.setContentType("text/html");
		p.addHyperlinkListener(new Hyperactive());
		p.setEditable(false);
		Container cp = jf.getContentPane();
		cp.add(BorderLayout.NORTH, p);
		jf.pack();
		jf.setVisible(true);

	}

	private class Hyperactive implements HyperlinkListener {

		public void hyperlinkUpdate(HyperlinkEvent e) {
			final EventType eventType = e.getEventType();
			if (eventType == HyperlinkEvent.EventType.ACTIVATED) {
				JEditorPane pane = (JEditorPane) e.getSource();
				if (e instanceof HTMLFrameHyperlinkEvent) {
					System.out.println("Processing HTMLFrameHyperLink");
					HTMLFrameHyperlinkEvent  evt = (HTMLFrameHyperlinkEvent)e;
					HTMLDocument doc = (HTMLDocument)pane.getDocument();
					doc.processHTMLFrameHyperlinkEvent(evt);
				} else {
					final URL url = e.getURL();
					if (url == null) {
						System.err.println("Error: URL == null");
						return;
					}
					if (url.getProtocol().equals("file") && url.getFile().startsWith("/bean:")) {
						System.out.println("Processing bean url: " + url);
						return;
					}
					try {
						System.out.println("Processing regular URL");
						pane.setPage(url);
					} catch (Exception t) {
						t.printStackTrace();
					}
				}
			} else {
				System.out.println("Ignoring event of type " + eventType);
			}
		}
	}
}
