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
import java.net.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

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
 * BUG: Does not handle redirects yet!
 * Responses not adequate; need to check at least for 404-type errors!
 * Display in a Tree instead of a TextArea.
 * use Color coding to indicate errors.
 * <p>
 * Further, it needs to use Swing and Threads properly (see
 * Java Swing, section on "MultiThreading Issues with Swing".
 * <p>
 * @author Ian Darwin, https://darwinsys.com.
 */
public class LinkChecker extends JFrame {

	private static final long serialVersionUID = 1444502541573633997L;

	/** The "global" activation flag: set true to halt. */
	protected volatile boolean done = false;

	/** The textfield for the starting URL.
	 * Should have a Properties file and a JComboBox instead.
	 */
	protected JTextField textFldURL;
	protected JButton checkButton;
	protected JButton saveButton;
	protected JButton killButton;
	protected JTextArea textWindow;
	protected int indent = 0;	// accessed single-threadedly
	protected List<String> cache = new ArrayList<String>();
  
	public static void main(String[] args) {
		LinkChecker lc = new LinkChecker();
		// If user gave a URL, start checking it right away
		if (args.length >= 1) {
			lc.textFldURL.setText(args[0]);
			lc.starter.actionPerformed(null);
		}
		lc.setVisible(true);
	}
	
	void setGUIStartable(boolean startable ) {
		checkButton.setEnabled(startable);
		killButton.setEnabled(!startable);
	}

	Executor threadPool = Executors.newSingleThreadExecutor();

	// Action listener used by both the text field (when
	// you hit return) and the explicit "Check URL" button.
	ActionListener starter = (e) -> {
		done = false;
		threadPool.execute(() -> {
			//System.out.println("LinkChecker.starter.execute()");
			setGUIStartable(false);
			cache.clear();
			final String urlString = textFldURL.getText();
			textWindow.setText("Checking " + urlString + "...");
			// May be long...
			checkOut(urlString);
			textWindow.append("\n-- All done --");
			setGUIStartable(true);
		});
	};

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

		textFldURL.addActionListener(starter);
		checkButton.addActionListener(starter);
		p.add(killButton = new JButton("Stop"));
		killButton.setEnabled(false);	// until we start Checking
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

  
	/** 
	 * MAIN WORK METHOD
	 * Start checking, given a URL by name.
	 * Calls checkLink to check each link.
	 */
	@SuppressWarnings("deprecation")
	public void checkOut(String rootURLString) {
		System.out.printf("LinkChecker.checkOut(%s)\n", rootURLString);
		URL rootURL = null;
		GetURLs urlGetter = null;

		if (done) {
			System.out.println("(done true)");
			return;
		}
		if (rootURLString == null) {
			textWindow.append("checkOut(null) isn't very useful");
			return;
		}
		if (cache.contains(rootURLString)) {
			System.out.println("Cached: not revisiting");
			return;	// already visited
		}
		cache.add(rootURLString);

		// Open the root URL for reading. May be a filename or a real URL.
		try {
			try {
				rootURL = URI.create(rootURLString).toURL();
			} catch (MalformedURLException e) {
				System.out.println(rootURLString + ": not a valid URL, trying as a file.");
				rootURL = new File(rootURLString).toURL();
			}
			System.out.println("rootURL = " + rootURL);
			// Either way, now try to open it.
			urlGetter = new GetURLs(rootURL);
			System.out.println("urlGetter = " + urlGetter);

			// If we're still here, the root URL given is OK.
			// Next we make up a "directory" URL from it.
			String rootURLdirString;
			if (rootURLString.endsWith("/") ||
				rootURLString.endsWith("\\")) {
				System.out.println("endswith /");
				rootURLdirString = rootURLString;
			} else {
				System.out.println("Doesnt end w/ /");
				int split = rootURLString.lastIndexOf('/');
				if (split == -1) {
					rootURLdirString = ".";
				} else {
					rootURLdirString = 
						rootURLString.substring(0, split);
				}
			}
			System.out.println("rootURLdirString now " + rootURLdirString);

			// Where we loop over the anchors in this page:

			urlGetter.reader.setWantedTags(GetURLs.wantTags);
			List<Element> urlTags = urlGetter.reader.readTags();
			System.out.println("TAGS:" + urlTags);
			for (Element tag : urlTags) {
				System.out.println("LinkChecker.checkOut(): " + tag);
				if (done)
					return;

				String href = tag.getAttribute("href");
				// Can't really validate these!
				if (href == null) {
					// textWindow.append(" null? !!\n");
					continue;
				}

				for (int j=0; j<indent; j++)
					textWindow.append("\t");

				textWindow.append(href);
				textWindow.append(" ");

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
				// checking a local web site with no web access available.

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
		} catch (FileNotFoundException e) {
			textWindow.append("Can't open file " + rootURLString + "\n");
			return;
		} catch (IOException e) {
			textWindow.append("reading " + rootURLString + " failed " + e + "\n");
		}
	}

	/** Check one link, given its DocumentBase and the tag */
	public String checkLink(URL linkURL) {

		try { 
			// Open it; if the open fails we'll likely throw an exception
			URLConnection luf = linkURL.openConnection();
			final String protocol = linkURL.getProtocol();
			if (protocol.equals("http") || protocol.equals("https")) {
				HttpURLConnection huf = (HttpURLConnection)luf;
				final int responseCode = huf.getResponseCode();
				return (responseCode == -1) ?
					"Server error: bad HTTP response " + responseCode :
					responseCode + " " + huf.getResponseMessage();
			} else if (linkURL.getProtocol().equals("ftp")) {
				return "(skipping FTP link)";
			} else if (linkURL.getProtocol().equals("file")) {
				InputStream is = luf.getInputStream();
				is.close();
				// If that didn't throw an exception, the file is probably OK
				return "(File)";
			} else
				return "(non-HTTP)";
		}
		catch (IOException e) {
			return "DEAD " + e.toString();
		}
    }
}
