import java.awt.*;
import java.awt.event.*;
import java.net.*;
import java.io.*;

/** A simple HTML Link Checker. 
 * Sorta working, but not ready for prime time.
 * Need to start a Thread and be able to stop it, in case we get into a loop.
 * Need to have a -depth N argument to limit depth of checking.
 * Responses not really adequate; should check at least for 404-type errors!
 *
 * @author	Ian Darwin, Darwin Open Systems, www.darwinsys.com.
 * Routine "readTag" stolen shamelessly from from
 * Elliott Rusty Harold's "ImageSizer" program.
 */
public class LinkChecker extends Frame {
	protected TextField textFldURL;
	protected TextArea textWindow;
	protected Panel p;
	protected Button checkButton;
	protected Button killButton;
  
	public static void main(String[] args) {
		LinkChecker lc = new LinkChecker();
		if (args.length == 1)
			lc.textFldURL.setText(args[0]);
		lc.setSize(400, 350);
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
		textFldURL = new TextField(40);
		add("North", textFldURL);
		textWindow = new TextArea(80, 40);
		add("Center", textWindow);
		add("South", p = new Panel());
		p.add(checkButton = new Button("Check URL"));
		checkButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textWindow.setText("");
				checkAt(textFldURL.getText());
				textWindow.append("-- All done --");
			}
		});
		p.add(killButton = new Button("Stop"));
			killButton.setEnabled(false); // not handled
	}
  
	public void checkAt(String pageURL) {
		String thisLine;
		URL root;

		if (pageURL != null) {
			// Open the URL for reading
			try {
				if (pageURL.indexOf(":") != -1) {
					root = new URL(pageURL);
				}
				else {
					root = new URL("http://" + pageURL);
				}
				findLinks(root);
			}
			catch (MalformedURLException e) {
				System.err.println(pageURL + 
					" is not a parseable URL");
				System.err.println(e);
			}
		}
	}

	static int indent = 0;
	public void findLinks(URL u) {
		BufferedReader inrdr = null;
		char thisChar = 0;
		String tag = null;
  
		indent++;
		try {
			inrdr = new BufferedReader(new InputStreamReader(u.openStream()));
			int i;
			while ((i = inrdr.read()) != -1) {
				thisChar = (char)i;
				if (thisChar == '<') {
					tag = readTag(inrdr);
					// System.out.println("TAG: " + tag);
					if (tag.toUpperCase().startsWith("<A ") ||
						tag.toUpperCase().startsWith("<A\t")) {
						// for (int j=0; j<2*indent; j++)
						//	textWindow.append(" ");
						textWindow.append(tag + " -- ");
						// don't combine previous append with this one,
						// since this one can throw an exception!
						textWindow.append(checkLink(u, tag) + "\n");
					}
				}
			}
			inrdr.close();
		}
		catch (IOException e) {
		       textWindow.append("Error reading from " + u+tag + ":" + e);
		}
	}

	/** Check one link, given its DocumentBase and the tag */
	public String checkLink(URL u, String tag) {
    String s1 = tag.toUpperCase();
	URL linkURL;

      int p1, p2, p3, p4;
      p1 = s1.indexOf("HREF");
      p2 = s1.indexOf ("=", p1);
      p3 = s1.indexOf("\"", p2);
      p4 = s1.indexOf("\"", p3+1);
      String thisURL = tag.substring(p3+1, p4);
      try {
        if (thisURL.indexOf(":")  == -1) {
          // it's not an absolute URL
          linkURL = new URL(u, thisURL);
        } else {
          linkURL = new URL(thisURL);
        }
		URLConnection luf = linkURL.openConnection();
		findLinks(linkURL);	// it opened, so check it!
		//luf.close();
		}
      catch (MalformedURLException e) {
        return "MALFORMED";
      }
      catch (IOException e) {
		return "DEAD";
      }
		return "OK";    
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
		   System.err.println("I/O Error: " + e);
		}     
		catch (Exception e) {
		   System.err.println(e);
		}     

		return theTag.toString();
	}
}
