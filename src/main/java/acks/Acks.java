package acks;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Label;
import java.awt.Panel;
import java.awt.Rectangle;
import java.awt.TextArea;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.Enumeration;
import java.util.List;
import java.util.Properties;
import java.util.StringTokenizer;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 * OneAck - popup some dialogs, to thank some people in the Java style.
 */
class OneAck extends Frame {
	private static final long serialVersionUID = -8156044777264483555L;
	final static int COLS=45;
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
		add(ta = new TextArea(s.toString(), rows, COLS), BorderLayout.NORTH);

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
public class Acks extends JFrame {

	private static final long serialVersionUID = -6758017663808368177L;
	/** The default name to read/write */
	final static String DATAFILENAME = "Acks.dat";
	/** The current name to read/write */
	String acksFileName = null;
	/** The list of windows that have been added */
	List<OneAck> winList = new Vector<OneAck>();

	/** Main method, just instantiate and show.  */
	public static void main(String[] argv) {
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
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		acksFileName = fname;
		Properties p = new Properties();

		try {
			// Create input file to load from.
			FileInputStream ifile = new FileInputStream(fname);

			p.load(ifile);
		} catch (FileNotFoundException notFound) {
			System.err.println(notFound);
			throw new RuntimeException("Read error", notFound);
		} catch (IOException badLoad) { 
			System.err.println(badLoad);
			throw new RuntimeException("Read error", badLoad);
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
		add(lab1, BorderLayout.NORTH);
		Label lab2 = 
			new Label("The author thanks everyone mentioned here.",
			Label.CENTER);
		lab2.setFont(bigFont);
		add(lab2, BorderLayout.CENTER);
		add(cp = new Panel(), BorderLayout.SOUTH);
			Button b;
			cp.add(b = new Button("New"));
			b.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					OneAck n = new OneAck("New", "new", "NO TEXT YET",
						100, 100, 200, 100);
					System.out.println("Added " + n);
					n.setVisible(true);
					winList.add(n);
				}
			});
			cp.add(b = new Button("Save"));
			b.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					try {
						saveAll(winList, acksFileName);
					} catch(IOException exc) {
						//System.err.println("Error in save: " + exc);
						JOptionPane.showMessageDialog(Acks.this,
							"I/O Error in save!\n" + exc,	// message
							"I/O ERROR!?",				// titlebar
							JOptionPane.ERROR_MESSAGE);	// icon
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
				winList.add(one);
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
	protected synchronized void saveAll(List<OneAck> v, String fname) throws IOException {
		PrintWriter pf = new PrintWriter(new FileWriter(fname));
		pf.println("# Created by Acks @ " + new Date());
		pf.println("courseTitle=" + getTitle());
		for (int i=0; i<v.size(); i++) {
			OneAck o = v.get(i);
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

