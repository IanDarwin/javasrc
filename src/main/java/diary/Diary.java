import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;

/** This just maintains a text file as a Diary, with TimeStamps.
 */
public class Diary extends Frame {
	/** The width of the text */
	public static final int COLS = 70;
	/** The TextArea which displays the text, and does most of the work. */
	TextArea tf;
	/** The Version name and number */
	public final static String VERSION = "Java Diary 0.0";
	/** The default filename, if none given on the command line */
	public final static String DEFAULT_FILE_NAME = "Diary.txt";
	/** The actual filename */
	protected String fileName = null;

	/** "main program" method - construct and show */
	public static void main(String argv[]) {

		// create an Diary object, tell it to show up
		Diary d = new Diary();
		if (argv.length == 1)
			d.setFile(argv[0]);
		else
			d.setFile(DEFAULT_FILE_NAME);
		d.setVisible(true);
	}

	/** Construct the object including its GUI */
	public Diary() {
		super("My Diary (" + VERSION + ")");

		MenuItem mi;

		add(BorderLayout.NORTH, new Label(VERSION, Label.CENTER));

		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				setVisible(false);
				dispose();
				System.exit(0);
			}
		});
		MenuBar mb = new MenuBar();
		setMenuBar(mb);

		ResourceBundle b = ResourceBundle.getBundle("DiaryMenus");
		String menuLabel;
		try { menuLabel = b.getString("file"+".label"); }
		catch (MissingResourceException e) { menuLabel="file"; }

		Menu fm = new Menu(menuLabel);
		fm.add(mi = mkMenuItem(b, "file", "open"));
		fm.add(mi = mkMenuItem(b, "file", "new"));
		mi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fileName = null;
				tf.setText("");
			}
		});
		fm.add(mi = mkMenuItem(b, "file", "save"));
		mi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				saveFile(null);
			}
		});
		fm.addSeparator();
		fm.add(mi = mkMenuItem(b, "file", "exit"));
		mi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Diary.this.setVisible(false);
				Diary.this.dispose();
				System.exit(0);
			}
		});
		mb.add(fm);

		Menu vm = mkMenu(b,  "edit");
		vm.add(mi = mkMenuItem(b, "edit", "format"));
		mi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				format();
			}
		});
		vm.add(mi = mkMenuItem(b, "edit", "timestamp"));
		mi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tf.append("\n" + new Date().toString() + "\n");
				}
		});
		mb.add(vm);

		Menu hm = mkMenu(b,  "help");
		hm.add(mi = mkMenuItem(b, "help", "about"));
		mi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new InfoDialog(Diary.this, VERSION, 
				VERSION + "\n" +
				"Copyright (c) 1997 Ian F. Darwin\n" +
				"Free via email from ian@darwinsys.com").show();
			}
		});
		mb.setHelpMenu(hm);		// needed for portability (Motif, etc.).

		add(BorderLayout.CENTER, tf = new TextArea(24,COLS));

		pack();

		// After packing the Frame, centre it on the screen.
		Dimension us = getSize(), 
			them = Toolkit.getDefaultToolkit().getScreenSize();
		int newX = (them.width - us.width) / 2;
		int newY = (them.height- us.height)/ 2;
		setLocation(newX, newY);
	}

	/** Format the selection (like unix fmt) */
	void format() {
		// "The 50-line text formatter" in 15 lines.
		int col = 0;
		int start = tf.getSelectionStart();
		int end   = tf.getSelectionEnd();
		StringTokenizer st = new StringTokenizer(tf.getSelectedText());
		StringBuffer s = new StringBuffer();
		while (st.hasMoreTokens()) {
			String sval = st.nextToken();
			if (col+sval.length() > COLS-7) {
				s.append("\n");
				col = 0;
			}
			s.append(sval).append( );
			col+=sval.length()+1;
		}
		tf.replaceRange(s.toString(), start, end);
	}
	void setFile(String fn) {
		BufferedReader is = null;
		String s;
		tf.setText("");
		try {
			is = new BufferedReader(new FileReader(fn));
			fileName = fn;	// only if it opened!
			while ((s = is.readLine()) != null)
				tf.append(s + "\n");
			is.close();
		} catch (IOException e) {
			System.out.println(e);
		}
	}

	void saveFile(String fn) {
		if (fn == null)
			fn = fileName;
		if (fn /*still*/ == null)
			fn = DEFAULT_FILE_NAME;
		PrintWriter os = null;
		String s;
		try {
			s = tf.getText();
			os = new PrintWriter(new FileWriter(fn));
			os.print(s);
		} catch (IOException e) {
			System.out.println(e);
		} finally {
			if (os != null)
				os.close();
		}
	}

	/** Convenience routine to make a Menu */
	public Menu mkMenu(ResourceBundle b, String name) {
		String menuLabel;
		try { menuLabel = b.getString(name+".label"); }
		catch (MissingResourceException e) { menuLabel=name; }
		return new Menu(menuLabel);
	}

	/** Convenience routine to make a MenuItem */
	public MenuItem mkMenuItem(ResourceBundle b, String menu, String name) {
		String miLabel;
		try { miLabel = b.getString(menu + "." + name + ".label"); }
		catch (MissingResourceException e) { miLabel=name; }
		String key = null;
		try { key = b.getString(menu + "." + name + ".key"); }
		catch (MissingResourceException e) { key=null; }

		if (key == null)
			return new MenuItem(miLabel);
		else
			return new MenuItem(miLabel, new MenuShortcut(key.charAt(0)));
	}

}
