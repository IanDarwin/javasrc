import java.awt.*;
import java.awt.event.*;
import sun.net.www.protocol.file.FileURLConnection;
import java.net.*;
import java.io.*;

/** A simple HTML Link Checker. 
 * Sorta working, but not ready for prime time.
 * Needs code simplification/elimination.
 * Need a Properties file to set depth, URLs to check. etc.
 * Responses not adequate; need to check at least for 404-type errors!
 * When all that is (said and) done, display in a Tree instead of a TextArea.
 * Then use Color coding to indicate errors.
 *
 * @author Ian Darwin, Darwin Open Systems, www.darwinsys.com.
 * @version $Id$
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
		lc.setSize(500, 400);
		lc.setLocation(150, 150);
		lc.setVisible(true);
		if (args.length == 0)
			return;
		for (int i=0; i<args.length; i++) {
			lc.textFldURL.setText(args[0]);
			lc.startChecking();
		}
	}
  
	public void startChecking() {
		done = false;
		checkButton.setEnabled(false);
		killButton.setEnabled(true);
		textWindow.setText("");
		doCheck();
	}
	public void stopChecking() {
		done = true;
		checkButton.setEnabled(true);
		killButton.setEnabled(false);
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
				startChecking();
			}
		});
		p.add(killButton = new Button("Stop"));
		killButton.setEnabled(false);	// until startChecking is called.
		killButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (t == null || !t.isAlive())
					return;
				stopChecking();
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
	public void checkOut(String rootURLString) {
		URL rootURL = null;
		GetURLs urlGetter = null;

		if (rootURLString == null) {
			textWindow.append("checkOut(null) isn't very useful");
			return;
		}

		// Open the root URL for reading
		try {
			rootURL = new URL(rootURLString);
			urlGetter = new GetURLs(rootURL);
		} catch (MalformedURLException e) {
			textWindow.append("Can't parse " + rootURLString + "\n");
			return;
		} catch (FileNotFoundException e) {
			textWindow.append("Can't open file " + rootURLString + "\n");
			return;
		} catch (IOException e) {
			textWindow.append("openStream " + rootURLString + " " + e + "\n");
			return;
		}

		// If we're still here, the root URL given is OK.
		// Next we make up a "directory" URL from it.
		String rootURLdirString;
		if (rootURLString.endsWith("/") ||
			rootURLString.endsWith("\\"))
				rootURLdirString = rootURLString;
		else {
			rootURLdirString = rootURLString.substring(0, 
				rootURLString.lastIndexOf('/'));	// XXX or \
		}

		try {
			ArrayList urlTags = urlGetter.getURLs();
			Iterator urlIterator = urls.iterator();
			while (urlIterator.hasNext()) {
				String tag = urlIterator.next();
				System.out.println(tag);
						
				String href = extractHREF(tag);

				// Can't really validate these!
				if (href.startsWith("mailto:"))
					continue;

				if (href.startsWith("..") || href.startsWith("#"))
					// nothing doing!
					continue; 

				for (int j=0; j<indent; j++)
					textWindow.append("\t");

				textWindow.append(href + " -- ");
				// don't combine previous append with this one,
				// since this one can throw an exception!
				textWindow.append(checkLink(rootURL, href) + "\n");

				// If HTML, check it recursively
				if (href.endsWith(".htm") ||
					href.endsWith(".html")) {
						++indent;
						if (href.indexOf(':') != -1)
							checkOut(href);
						else {
							String newRef = 
								 rootURLdirString + '/' + href;
							// System.out.println("MADE " + newRef);
							checkOut(newRef);
						}
						--indent;
				}
			}
			urlGetter.close();
		} catch (IOException e) {
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
