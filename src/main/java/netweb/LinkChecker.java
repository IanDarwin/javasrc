package netweb;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.SocketException;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import com.darwinsys.io.FileIO;
import com.darwinsys.swingui.UtilGUI;

/** A simple HTML Link Checker. 
 * Need a Properties file to set depth, URLs to check. etc.
 * Responses not adequate; need to check at least for 404-type errors!
 * When all that is (said and) done, display in a Tree instead of a TextArea.
 * Then use Color coding to indicate errors.
 * <p>
 * Further, it needs to use Swing and Threads properly (see
 * Java Swing, section on "MultiThreading Issues with Swing".
 * As it stands, the GUI thread is locked up until the complete
 * checking is completed, which could take a long time.
 * <p>
 * TODO: parse using an XML parser, though this would fail for most web
 * sites today (and how much longer do you think we must maintain
 * so much ad-hoc code for parsing the almost-regular kludge known as HTML?)
 *
 * @author Ian Darwin, Darwin Open Systems, www.darwinsys.com.
 */
public class LinkChecker extends JFrame {

	private static final long serialVersionUID = 1444502541573633997L;

	/** The "global" activation flag: set true to halt. */
	protected boolean done = false;

	/** The textfield for the starting URL.
	 * Should have a Properties file and a JComboBox instead.
	 */
	protected JTextField textFldURL;
	protected JButton checkButton;
	protected JButton saveButton;
	protected JButton killButton;
	protected JTextArea textWindow;
	protected int indent = 0;
	protected Map<String,Boolean> hash = new HashMap<String,Boolean>();
  
	public static void main(String[] args) {
		LinkChecker lc = new LinkChecker();
		if (args.length >= 1)
			lc.textFldURL.setText(args[0]);
		lc.setVisible(true);
	}
	
	void setGUIStartable(boolean startable ) {
		checkButton.setEnabled(startable);
		killButton.setEnabled(!startable);
	}
  
	/** Construct a LinkChecker */
	public LinkChecker() {
		super("LinkChecker");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container cp = getContentPane();
		cp.setLayout(new BorderLayout());
		JPanel p = new JPanel();
		p.setLayout(new FlowLayout());
		p.add(new JLabel("URL"));
		p.add(textFldURL = new JTextField(30));
		p.add(checkButton = new JButton("Check URL"));

		// Make a single action listener for both the text field (when
		// you hit return) and the explicit "Check URL" button.
		ActionListener starter = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				done = false;
				setGUIStartable(true);
				Thread t = new Thread() {
					public void run() {
						textWindow.setText("Checking...");
						setGUIStartable(false);
						checkOut(textFldURL.getText());
						textWindow.append("-- All done --");
						setGUIStartable(true);
					}
				};
				t.start();
			}
		};
		textFldURL.addActionListener(starter);
		checkButton.addActionListener(starter);
		p.add(killButton = new JButton("Stop"));
		killButton.setEnabled(false);	// until startChecking is called.
		killButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				done = true;
			}
		});
		p.add(saveButton = new JButton("Save Log"));
		saveButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
			try {
				String log = textWindow.getText();
				String fileName = "linkchecker.log";
				FileIO.stringToFile(log, fileName);
				JOptionPane.showMessageDialog(LinkChecker.this,
					"File saved as " + fileName, "Done",
					JOptionPane.INFORMATION_MESSAGE);
			} catch (IOException ex) {
				JOptionPane.showMessageDialog(LinkChecker.this,
					"IOError",
					ex.toString(),
					JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		// Now lay out the main GUI - URL & buttons on top, text larger
		cp.add(p, BorderLayout.NORTH);
		textWindow = new JTextArea(80, 40);
		cp.add(new JScrollPane(textWindow), BorderLayout.CENTER);
		UtilGUI.maximize(this);
	}

  
	/** Start checking, given a URL by name.
	 * Calls checkLink to check each link.
	 */
	@SuppressWarnings("deprecation")
	public void checkOut(String rootURLString) {
		URL rootURL = null;
		GetURLs urlGetter = null;

		if (done)
			return;
		if (rootURLString == null) {
			textWindow.append("checkOut(null) isn't very useful");
			return;
		}
		if (hash.get(rootURLString) != null) {
			return;	// already visited
		}
		hash.put(rootURLString, Boolean.TRUE);

		// Open the root URL for reading. May be a filename or a real URL.
		try {
			try {
				rootURL = new URL(rootURLString);
			} catch (MalformedURLException e) {
				// Neat Trick: if not a valid URL, try again as a file.
				rootURL = new File(rootURLString).toURL();
			}
			// Either way, now try to open it.
			urlGetter = new GetURLs(rootURL);
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
				rootURLString.lastIndexOf('/'));
		}

		try {
			urlGetter.reader.setWantedTags(GetURLs.wantTags);
			List<Element> urlTags = urlGetter.reader.readTags();
			for (Element tag : urlTags) {
				if (done)
					return;
						
				String href = extractHREF(tag.toString());

				for (int j=0; j<indent; j++)
					textWindow.append("\t");
				textWindow.append(href + " -- ");

				// Can't really validate these!
				if (href == null) {
					textWindow.append(" null? !!\n");
					continue;
				}
				if (href.startsWith("mailto:")) {
					textWindow.append(href + " -- not checking\n");
					continue;
				}

				if (href.startsWith("..") || href.startsWith("#")) {
					textWindow.append(href + " -- not checking\n");
					// nothing doing!
					continue; 
				}

				URL hrefURL = new URL(rootURL, href);

				// TRY THE URL.
				// (don't combine previous textWindow.append with this one,
				// since this one can throw an exception)
				textWindow.append(checkLink(hrefURL));

				// There should be an option to control whether to
				// "try the url" first and then see if off-site, or
				// vice versa, for the case when checking a site you're
				// working on on your notebook on a train in the Rockies
				// with no web access available.

				// Now see if the URL is off-site.
				if (!hrefURL.getHost().equals(rootURL.getHost())) {
					textWindow.append("-- OFFSITE -- not following\n");
					continue;
				}
				textWindow.append("\n");

				// If HTML, check it recursively. No point checking
				// PHP, CGI, JSP, etc., since these usually need forms input.
				// If a directory, assume HTML or something under it will work.
				if (href.endsWith(".htm") ||
					href.endsWith(".html") ||
					href.endsWith("/")) {
						++indent;
						if (href.indexOf(':') != -1)
							checkOut(href);			// RECURSE
						else {
							String newRef = 
								 rootURLdirString + '/' + href;
							checkOut(newRef);		// RECURSE
						}
						--indent;
				}
			}
			urlGetter.close();
		} catch (IOException e) {
			System.err.println("Error: (" + e +")");
		}
	}

	/** Check one link, given its DocumentBase and the tag */
	public String checkLink(URL linkURL) {

		try { 
			// Open it; if the open fails we'll likely throw an exception
			URLConnection luf = linkURL.openConnection();
			if (linkURL.getProtocol().equals("http")) {
				HttpURLConnection huf = (HttpURLConnection)luf;
				String s = huf.getResponseCode() + " " + huf.getResponseMessage();
				if (huf.getResponseCode() == -1)
					return "Server error: bad HTTP response";
				return s;
			} else if (linkURL.getProtocol().equals("file")) {
				InputStream is = luf.getInputStream();
				is.close();
				// If that didn't throw an exception, the file is probably OK
				return "(File)";
			} else
				return "(non-HTTP)";
		}
		catch (SocketException e) {
			return "DEAD: " + e.toString();
		}
		catch (IOException e) {
			return "DEAD";
		}
    }
 
	/** Extract the URL from <sometag attrs HREF="http://foo/bar" attrs ...> 
	 * We presume that the HREF is correctly quoted!!!!!
	 */
	public String extractHREF(String tag) throws MalformedURLException {
		String caseTag = tag.toLowerCase(), attrib;
		int p1, p2, p3, p4;

		if (caseTag.startsWith("<a") && 
			Character.isWhitespace(caseTag.charAt(2))) {
			attrib = "href";		// A
		} else if (caseTag.startsWith("<applet ")){
			attrib = "code";
		} else
			attrib = "src";			// image, frame
		// XXX refactor to use an enum here
		if (attrib.equals("href") && caseTag.indexOf("name") != -1) {
			return null;		// silently ignore <a name=...>
		}
		p1 = caseTag.indexOf(attrib);
		if (p1 < 0) {
			throw new MalformedURLException("Can't find " + attrib + " in " + tag);
		}
		p2 = tag.indexOf ("=", p1);

		// This fails to handle unquoted href, which some dinosaurs insist
		// on using, saying the parser can sort it out. Phhhhhhhht!!!!
		// XXX should handle single-quoted hrefs here
		p3 = tag.indexOf("\"", p2);
		p4 = tag.indexOf("\"", p3+1);
		if (p3 < 0 || p4 < 0) {
			throw new MalformedURLException("Invalid " + attrib + " in " + tag);
		}
		String href = tag.substring(p3+1, p4);
		return href;
	}
}
