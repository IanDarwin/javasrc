
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
import java.util.*;

/**
 * OneAck - popup some dialogs, to thank some people in the Java style.
 * @version $Id$
 */
class OneAck extends Frame {
	final int COLS=45;
	/** The properties name (same as title but with no spaces) */
	private String propsName;
	/** The TextArea */
	private TextArea ta;

	/** Construct a OneAck object to run an Acknowledgement window */
	public OneAck(String propsName, String title, String msg,
		int x, int y, int width, int height) {
		super(title);
		this.propsName=propsName;

		setLayout(new BorderLayout());
		int chars = msg.length();
		int rows = 1;		// at least 1 row

		// "The 50-line text formatter" in 15 lines.
		StringTokenizer st = new StringTokenizer(msg);
		StringBuffer s = new StringBuffer();
		for (int col=0; st.hasMoreTokens(); ) {
			String sval = st.nextToken();
			if (col+sval.length() > COLS) {
				s.append("\n");
				rows++;
				col = 0;
			}
			s.append(sval).append(' ');
			col+=sval.length()+1;
		}
		s.append("\n");

		// Each dialog has a TextArea on top...
		add("North", ta = new TextArea(s.toString(), rows, COLS));

		// And a panel with one push Button on the bottom.
		Panel p;
		add("South",  p = new Panel());
		Rectangle r = new Rectangle(x, y, width, height);
		// System.out.println(title+"->"+r);
		setBounds(r);
	}

	/** Present this OneAck as a String */
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append("OneAck[");
		sb.append(getTitle());
		sb.append('/');
		sb.append(getText());
		sb.append(",");
		sb.append(getBounds().toString());
		return sb.toString();
	}

	/** Return the properties name */
	public String getPropsName() {
		return propsName;
	}

	/** Return the current text message */
	public String getText() {
		return ta.getText();
	}
}

/**
 * Acks - pop up a bunch of dialogs, to thank some people in the Java style.
 */
public class Acks extends Frame {
	/** The default name to read/write */
	final static String DATAFILENAME = "Acks.dat";
	/** The current name to read/write */
	String acksFileName = null;
	/** The list of windows that have been added */
	Vector winList = new Vector();

	/** Main method, just instantiate and show.  */
	public static void main(String argv[]) {
		switch(argv.length) {
			case 0:
				new Acks(DATAFILENAME).setVisible(true);
				break;
			case 1:
				new Acks(argv[0]).setVisible(true);
				break;
			default:
				throw new IllegalArgumentException("Usage: Acks [datafile]");
		}
	}

	/** Construct an Acks object, by making a ton of OneAcks from list */
	Acks(String fname) {
		super();
		acksFileName = fname;
		Properties p = new Properties();

		try {
			// Create input file to load from.
			FileInputStream ifile = new FileInputStream(fname);

			p.load(ifile);
		} catch (FileNotFoundException notFound) {
			System.err.println(notFound);
			System.exit(1);
		} catch (IOException badLoad) { 
			System.err.println(badLoad);
			System.exit(1);
		}

		String courseTitle = p.getProperty("courseTitle");	// no "."!
		if (courseTitle != null)
			setTitle(courseTitle);

		Panel cp;
		Font bigFont = new Font("Helvetica", Font.BOLD, 18);
		setLayout(new BorderLayout());
		Label lab1 = 
			new Label(courseTitle, Label.CENTER);
		lab1.setFont(bigFont);
		add("North", lab1);
		Label lab2 = 
			new Label("The author thanks everyone mentioned here.",
			Label.CENTER);
		lab2.setFont(bigFont);
		add("Center", lab2);
		add("South", cp = new Panel());
			Button b;
			cp.add(b = new Button("New"));
			b.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					OneAck n = new OneAck("New", "new", "NO TEXT YET",
						100, 100, 200, 100);
					System.out.println("Added " + n);
					n.setVisible(true);
					winList.addElement(n);
				}
			});
			cp.add(b = new Button("Save"));
			b.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					try {
						saveAll(winList, acksFileName);
					} catch(IOException exc) {
						System.err.println("Error in save: " + exc);
						// JOptionPane.showMessageDialog(Acks.this,
							// "I/O Error in save!\n" + exc,	// message
							// "I/O ERROR!?",				// titlebar
							// JOptionPane.ERROR_MESSAGE);	// icon
					}
				}
			});
			cp.add(b = new Button("Quit"));
			b.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					setVisible(false);
					dispose();
					// guard against Exit while saveAll still running!
					synchronized(this) {
						System.exit(0);
					}
				}
			});
		pack();

		// Now centre our "main" window on the screen.
		Dimension us = getSize(), 
			them = Toolkit.getDefaultToolkit().getScreenSize();
		int newX = (them.width - us.width) / 2;
		int newY = (them.height- us.height)/ 2;
		setLocation(newX, newY);


		// Now get a list of all the properties in the file
		Enumeration e = p.propertyNames();

		// And for each one that is a title, process the corresponding values.
		while (e.hasMoreElements()) {
			// get next property name from the enumeration...
			String key = (String)e.nextElement();

			if (key.endsWith(".title")) {
				String baseTitle  = key.substring(0, key.length()-".text".length()-1);
				// System.out.println("baseTitle=" + baseTitle);
				String title = p.getProperty(baseTitle + ".title", "Untitled!");
				String text = p.getProperty(baseTitle + ".text", "MISSING TEXT!");
				String x = p.getProperty(baseTitle + ".x", "0");
				String y = p.getProperty(baseTitle + ".y", "0");
				String width = p.getProperty(baseTitle + ".width", "200");
				String height = p.getProperty(baseTitle + ".height", "100");

				OneAck one = new OneAck(baseTitle, title, text,
					Integer.parseInt(x),
					Integer.parseInt(y),
					Integer.parseInt(width),
					Integer.parseInt(height));
				// System.out.println("Added " + one);
				one.setVisible(true);
				winList.addElement(one);
			}
		}
	}

	/* "Save" all the items with locations etc in NEW FORMAT.
	 * name.title=This is a test
	 * name.x=42
	 * name.y=100
	 * name.width=200
	 * name.height=300
	 * For now, just print them.
	 * Once both files are saved in new format, replace this
	 * with a call to "properties.save"
	 */
	protected synchronized void saveAll(Vector v, String fname) throws IOException {
		PrintWriter pf = new PrintWriter(new FileWriter(fname));
		pf.println("# Created by Acks @ " + new Date());
		pf.println("courseTitle=" + getTitle());
		for (int i=0; i<v.size(); i++) {
			OneAck o = (OneAck)v.elementAt(i);
			String name = o.getPropsName();
			Rectangle r = o.getBounds();
			pf.println(name + ".title=" + o.getTitle());
			pf.println(name + ".text=" + o.getText().replace('\n', ' '));
			pf.println(name + ".x=" + r.x);
			pf.println(name + ".y=" + r.y);
			pf.println(name + ".width=" + r.width);
			pf.println(name + ".height=" + r.height);
		}
		pf.close();
	}
}

