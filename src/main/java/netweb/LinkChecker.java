import java.awt.*;
import java.awt.event.*;
import sun.net.www.protocol.file.FileURLConnection;
import java.net.*;
import java.io.*;

/** A simple HTML Link Checker. 
 * Sorta working, but not ready for prime time.
 * Finish file: url checking, but this is hard:
 * The biggest problem is that Netscape and Java have different ideas
 * on how to do File: URLs, and they're incompatible.
 * Needs code simplification/elimination.
 * Need a Properties file to set depth, URLs to check. etc.
 * Responses not adequate; need to check at least for 404-type errors!
 * When all that is (said and) done, display in a Tree instead of a TextArea.
 *
 * @author	Ian Darwin, Darwin Open Systems, www.darwinsys.com.
 * Routine "readTag" stolen shamelessly from from
 * Elliott Rusty Harold's "ImageSizer" program.
 */
public class LinkChecker extends Frame implements Runnable {
	protected Thread t = null;
	boolean done = false;
	protected Panel p;
	protected TextField textFldURL;
	protected Button checkButton;
	protected Button killButton;
	protected TextArea textWindow;
	protected int indent = 0;
  
	public static void main(String[] args) {
		LinkChecker lc = new LinkChecker();
		if (args.length == 1)
			lc.textFldURL.setText(args[0]);
		lc.setSize(500, 400);
		lc.setLocation(150, 150);
		lc.setVisible(true);
	}
  
	/** Construct a LinkChecker */
	public LinkChecker() {
		super("LinkChecker");
        addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
			setVisible(false);
			dispose();
			System.exit(0);
			}
		});
		setLayout(new BorderLayout());
		p = new Panel();
		p.setLayout(new FlowLayout());
		p.add(new Label("URL"));
		p.add(textFldURL = new TextField(40));
		p.add(checkButton = new Button("Check URL"));
		checkButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				doCheck();
			}
		});
		p.add(killButton = new Button("Stop"));
		killButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (t == null || !t.isAlive())
					return;
				done = true;
			}
		});
		// Now lay out the main GUI - URL & buttons on top, text larger
		add("North", p);
		textWindow = new TextArea(80, 40);
		add("Center", textWindow);
	}

	public void doCheck() {
		if (t!=null && t.isAlive())
			return;
		t = new Thread(this);
		t.start();
	}

	public synchronized void run() {
		textWindow.setText("");
		checkOut(textFldURL.getText());
		textWindow.append("-- All done --");
	}
  
	/** Start checking, given a URL by name.
	 * Calls checkLink to check each link.
	 */
	public void checkOut(String pageURL) {
		String thisLine = null;
		URL root = null;

		if (pageURL == null) {
			throw new IllegalArgumentException(
				"checkOut(null) isn't very useful");
		}
		// Open the URL for reading
		try {
			root = new URL(pageURL);
			BufferedReader inrdr = null;
			char thisChar = 0;
			String tag = null;
  
			inrdr = new BufferedReader(new InputStreamReader(root.openStream()));
			int i;
			while ((i = inrdr.read()) != -1) {
				if (done) {
					textWindow.append("-- Interrupted --");
					return;
				}
				thisChar = (char)i;
				if (thisChar == '<') {
					tag = readTag(inrdr);
					// System.out.println("TAG: " + tag);
					if (tag.toUpperCase().startsWith("<A ") ||
						tag.toUpperCase().startsWith("<A\t")) {
						
						String href = extractHREF(tag);

						// Can't really validate these!
						if (href.startsWith("mailto:"))
							continue;

						for (int j=0; j<indent; j++)
							textWindow.append("\t");
						textWindow.append(href + " -- ");
						// don't combine previous append with this one,
						// since this one can throw an exception!
						textWindow.append(checkLink(root, href) + "\n");

						// If HTML, check it recursively
						if (href.endsWith(".htm") ||
							href.endsWith(".html")) {
								++indent;
								if (href.indexOf(":") != -1)
									checkOut(href);
								else {
									String newRef = root.getProtocol() +
										 "://" + root.getHost() + "/" + href;
									// System.out.println(newRef);
									checkOut(newRef);
								}
								--indent;
						}
					}
				}
			}
			inrdr.close();
		}
		catch (MalformedURLException e) {
			textWindow.append("Can't parse " + pageURL + "\n");
		}
		catch (IOException e) {
			System.err.println("Error " + ":(" + e +")");
		}
	}

	/** Check one link, given its DocumentBase and the tag */
	public String checkLink(URL baseURL, String thisURL) {
		URL linkURL;

		try {
			if (thisURL.indexOf(":")  == -1) {
			  // it's not an absolute URL
			  linkURL = new URL(baseURL, thisURL);
			} else {
			  linkURL = new URL(thisURL);
			}

			// Open it; if the open fails we'll likely throw an exception
			URLConnection luf = linkURL.openConnection();
			if (luf instanceof HttpURLConnection) {
				HttpURLConnection huf = (HttpURLConnection)luf;
				String s = huf.getResponseCode() + " " + huf.getResponseMessage();
				if (huf.getResponseCode() == -1)
					return "Server error: bad HTTP response";
				return s;
			} else if (luf instanceof FileURLConnection) {
				InputStream is = luf.getInputStream();
				is.close();
				// If that didn't throw an exception, the file is probably OK
				return "(File)";
			} else
				return "(non-HTTP)";
		}
		catch (MalformedURLException e) {
			return "MALFORMED";
		}
		catch (SocketException e) {
			return "DEAD: " + e.toString();
		}
		catch (IOException e) {
			return "DEAD";
		}
    }
 
	/** Read one tag. Adapted from code by Elliott Rusty Harold */
	public String readTag(BufferedReader is) {
		StringBuffer theTag = new StringBuffer("<");
		int i = '<';
	  
		try {
			while (i != '>' && (i = is.read()) != -1)
				theTag.append((char)i);
		}
		catch (IOException e) {
		   System.err.println("IO Error: " + e);
		}     
		catch (Exception e) {
		   System.err.println(e);
		}     

		return theTag.toString();
	}

	/** Extract the URL from <A HREF="http://foo/bar" ...> 
	 * We presume that the HREF is the first tag.
	 */
	public String extractHREF(String tag) throws MalformedURLException {
		String s1 = tag.toUpperCase();
		int p1, p2, p3, p4;
		p1 = s1.indexOf("HREF");
		p2 = s1.indexOf ("=", p1);
		p3 = s1.indexOf("\"", p2);
		p4 = s1.indexOf("\"", p3+1);
		if (p3 < 0 || p4 < 0)
			throw new MalformedURLException(tag);
		return tag.substring(p3+1, p4);
	}

}
