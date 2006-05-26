package diary;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.MissingResourceException;
import java.util.ResourceBundle;
import java.util.StringTokenizer;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;

import com.darwinsys.swingui.I18N;

/** This just maintains a text file as a Diary, with TimeStamps.
 */
public class Diary extends JFrame {

	private static final long serialVersionUID = 221550013572729175L;
	/** The width of the text */
	public static final int COLS = 70;
	/** The TextArea which displays the text, and does most of the work. */
	JTextArea tf;
	/** The Version name and number */
	public final static String VERSION = "Java Diary 0.0";
	/** The default filename, if none given on the command line */
	public final static String DEFAULT_FILE_NAME = "Diary.txt";
	/** The actual filename */
	protected String fileName = null;
	/** "main program" method - construct and show */
	public static void main(String[] argv) {

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
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


		add(BorderLayout.NORTH, new JLabel(VERSION, JLabel.CENTER));

		JMenuBar mb = new JMenuBar();
		setJMenuBar(mb);

		ResourceBundle b = ResourceBundle.getBundle("diary.DiaryMenus");
		String menuLabel;
		try { menuLabel = b.getString("file"+".label"); }
		catch (MissingResourceException e) { menuLabel="file"; }

		JMenu fm = new JMenu(menuLabel);
		JMenuItem mi;
		fm.add(mi = I18N.mkMenuItem(b, "file", "open"));
		mi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser chooser = getFileChooser();
				int returnVal = chooser.showOpenDialog(Diary.this);
				if (returnVal == JFileChooser.APPROVE_OPTION) {
					File file = chooser.getSelectedFile();
					setFile(file.getAbsolutePath());
				} else {
					System.out.println("You did not choose a filesystem object.");
				}
			}
		});
		fm.add(mi = I18N.mkMenuItem(b, "file", "new"));
		mi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fileName = null;
				tf.setText("");
			}
		});
		fm.add(mi = I18N.mkMenuItem(b, "file", "save"));
		mi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				saveFile(null);
			}
		});
		fm.addSeparator();
		fm.add(mi = I18N.mkMenuItem(b, "file", "exit"));
		mi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Diary.this.setVisible(false);
				Diary.this.dispose();
				System.exit(0);
			}
		});
		mb.add(fm);

		JMenu vm = I18N.mkMenu(b,  "edit");
		vm.add(mi = I18N.mkMenuItem(b, "edit", "format"));
		mi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				format();
			}
		});
		vm.add(mi = I18N.mkMenuItem(b, "edit", "timestamp"));
		mi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tf.append("\n" + new Date().toString() + "\n");
				}
		});
		mb.add(vm);

		JMenu hm = I18N.mkMenu(b,  "help");
		hm.add(mi = I18N.mkMenuItem(b, "help", "about"));
		mi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(Diary.this, 
				VERSION + "\n" +
				"Copyright (c) 1997-2006 Ian F. Darwin\n" +
				"Free download from http://javacook.darwinsys.com/",
				VERSION, JOptionPane.INFORMATION_MESSAGE);
			}
		});
		mb.add(hm);

		add(BorderLayout.CENTER, tf = new JTextArea(24,COLS));

		pack();

		// After packing the Frame, centre it on the screen.
		Dimension us = getSize(), 
			them = Toolkit.getDefaultToolkit().getScreenSize();
		int newX = (them.width - us.width) / 2;
		int newY = (them.height- us.height)/ 2;
		setLocation(newX, newY);
	}
	
	private JFileChooser chooser = null;
	protected JFileChooser getFileChooser() {
		if (chooser == null) {
			chooser = new JFileChooser();
//			JFileFilter filter = new JFileFilter();
//			filter.addType("java");
//			filter.addType("class");
//			filter.addType("jar");
//			filter.setDescription("Java-related files");
//			chooser.addChoosableFileFilter(filter);
		}
		return chooser;
	}

	/** Format the selection (like unix fmt) */
	void format() {
		// "The 50-line text formatter" in 15 lines.
		int col = 0;
		int start = tf.getSelectionStart();
		int end   = tf.getSelectionEnd();
		final String selectedText = tf.getSelectedText();
		if (selectedText == null) {
			JOptionPane.showMessageDialog(this, "Please select text before formatting");
			return;
		}
		StringTokenizer st = new StringTokenizer(selectedText);
		StringBuffer s = new StringBuffer();
		while (st.hasMoreTokens()) {
			String sval = st.nextToken();
			if (col+sval.length() > COLS-7) {
				s.append("\n");
				col = 0;
			}
			s.append(sval).append(' ');
			col+=sval.length()+1;
		}
		tf.replaceRange(s.toString(), start, end);
	}
	
	/**
	 * Specify the filename.
	 */
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

	/**
	 * Save the Model into the given file.
	 */
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

}
