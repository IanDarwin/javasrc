package gui;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Frame;
import java.awt.GraphicsEnvironment;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;
import java.awt.MenuShortcut;
import java.awt.Panel;
import java.awt.Toolkit;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

/** Unicode - show a page of Unicode characters.
 * BUG: Times throws a bunch of exceptions on page 2 and 3, that can
 * not be caught as they occur in the AWT thread. On some platforms.
 *
 * TODO - remove GoToPage as a dialog, move its textfield into main GUI.
 *
 * @author	Ian Darwin, http://www.darwinsys.com/
 */
public class Unicode extends JFrame {

	/** "main program" method - construct and show */
	public static void main(String[] av) {
		// create a Unicode object, tell it to show up
		new Unicode().setVisible(true);
	}

	protected final int COLUMNS = 16, ROWS = 16;
	/** the unicode char at start of current page */
	protected int startNum = 0;
	protected final int QUADSIZE = ROWS * COLUMNS;
	/** the buttons that display the characters */
	protected JLabel buttons[][] = new JLabel[COLUMNS][ROWS];
	/** the font name display */
	protected JLabel fontName;
	/** the row labels, in a column at the left */
	protected JLabel rowLabs[] = new JLabel[ROWS];
	/** The page chooser pop-up */
	protected GoToPage gotoPageUI;
	/** How git to make the font samples */
	protected final int FONTSIZE = 8;

	/** Construct the object including its GUI */
	public Unicode() {
		super("Unicode");

		Container cp = getContentPane();

		// Used both for Buttons and Menus
		ResourceBundle b = ResourceBundle.getBundle("UnicodeWidgets");

		JButton quitButton, nextButton, prevButton;
		Panel p = new Panel();
		// Make a grid, add one for labels.
		p.setLayout(new GridLayout(ROWS+1, COLUMNS+1));

		// Add first row, just column labels.
		p.add(new JLabel(""));
		for (int i = 0; i<COLUMNS; i++)
			p.add(new JLabel(Integer.toString(i, 16), JLabel.CENTER));

		// Add subsequent rows, each with an offset label
		for (int i = 0; i<ROWS; i++) {
			JLabel l = new JLabel("0000");	// room for max, i.e. \uFFFF
			p.add(l);
			rowLabs[i] = l;
			for (int j = 0 ; j < COLUMNS; j++) {
				JLabel pb = new JLabel(" ");
				buttons[j][i] = pb;
				p.add(pb);
			}
		}

		// ActionListeners for jumping around; used by buttons and menus
		ActionListener firster = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				gotoPage(startNum = 0);
			}
		};
		ActionListener previouser = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			if (startNum > 0)
				gotoPage(startNum -= QUADSIZE);
			}
		};
		ActionListener nexter = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			if (startNum < 65535)
				gotoPage(startNum += QUADSIZE);
			}
		};
		ActionListener laster = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				gotoPage(65536 - QUADSIZE);
			}
		};

		cp.add(BorderLayout.NORTH, p);
		fontName = new JLabel("Default font", JLabel.CENTER);
		cp.add(BorderLayout.CENTER, fontName);
		Panel q = new Panel();
		cp.add(BorderLayout.SOUTH, q);
		q.add(prevButton = mkButton(b, "page.prev"));
		prevButton.addActionListener(previouser);

		q.add(nextButton = mkButton(b, "page.next"));
		nextButton.addActionListener(nexter);

		q.add(quitButton = mkButton(b, "exit"));
		quitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				dispose();
				System.exit(0);
			}
		});
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				setVisible(false);
				dispose();
				System.exit(0);
			}
		});

		MenuItem mi;		// used in various spots

		MenuBar mb = new MenuBar();
		setMenuBar(mb);

		String titlebar;
		try { titlebar = b.getString("program"+".title"); }
		catch (MissingResourceException e) { titlebar="Unicode Demo"; }
		setTitle(titlebar);

		ActionListener fontSelector = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String font = e.getActionCommand();
				mySetFont(font, FONTSIZE);
			}
		};

		Menu fontMenu = mkMenu(b, "font");
		// String[] fontList = Toolkit.getDefaultToolkit().getFontList();
		String[] fontList = GraphicsEnvironment.getLocalGraphicsEnvironment().
			getAvailableFontFamilyNames();
		for (int i=0; i<fontList.length; i++) {
			fontMenu.add(mi = new MenuItem(fontList[i]));
			mi.addActionListener(fontSelector);
		}
		mb.add(fontMenu);

		gotoPageUI = new GoToPage("Unicode Page");
		centre(gotoPageUI);

		Menu vm = mkMenu(b,  "page");
		vm.add(mi = mkMenuItem(b, "page", "first"));
		mi.addActionListener(firster);
		vm.add(mi = mkMenuItem(b, "page", "prev"));
		mi.addActionListener(previouser);
		vm.add(mi = mkMenuItem(b, "page", "next"));
		mi.addActionListener(nexter);
		vm.add(mi = mkMenuItem(b, "page", "last"));
		mi.addActionListener(laster);
		vm.add(mi = mkMenuItem(b, "page", "goto"));
		mi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Unicode.this.gotoPageUI.setVisible(true);
			}
		});
		mb.add(vm);

		Menu hm = mkMenu(b,  "help");
		hm.add(mi = mkMenuItem(b, "help", "about"));
		mb.setHelpMenu(hm);		// needed for portability (Motif, etc.).

		pack();
		// After packing the Frame, centre it on the screen.
		centre(this);

		// start at a known place
		mySetFont(fontList[0], FONTSIZE);
		gotoPage(startNum);
	} // End of huge Constructor

	private void mySetFont(String font, int sz) {
		fontName.setText("Font = " + font);
		Font f = new Font(font, Font.PLAIN, sz);
		for (int i = 0; i<ROWS; i++) {
			for (int j = 0; j<COLUMNS; j++)
				buttons[i][j].setFont(f);
		}
		repaint();
	}

	public void centre(Window c) {
		Dimension us = c.getSize(), 
			them = Toolkit.getDefaultToolkit().getScreenSize();
		int newX = (them.width - us.width) / 2;
		int newY = (them.height- us.height)/ 2;
		c.setLocation(newX, newY);
	}

	/** Go to a given page of Unicode. 
	 * At present the parameter is a code value, but it should be a page #.
	 */
	private void gotoPage(int startNum) {
		// System.out.println("startAt(" + startNum + ")");
		char chars[] = new char[1];
		for (int i = 0; i<ROWS; i++) {
			JLabel l = rowLabs[i];
			// System.out.println("i=" + i + ", JLabel=" + l);
			l.setText(Integer.toString(startNum+(i*COLUMNS), 16));
			// l.validate();		// size may be too big now
			for (int j = 0; j<COLUMNS; j++) {
				chars[0] = (char)(startNum+((j*ROWS)+i));
				JLabel b = buttons[i][j];
				b.setText(new String(chars));
			}
		}
		repaint();
	}

	/** Convenience routine to make a Button */
	public JButton mkButton(ResourceBundle b, String name) {
		String label;
		try { label = b.getString(name+".label"); }
		catch (MissingResourceException e) { label=name; }
		return new JButton(label);
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

	/** Implement a simple "Go To Page" dialog
	 * Row one: "Go to Page", textfield
	 * second OK, Cancel buttons.
	 */
	class GoToPage extends Frame {
		/** TextField used to enter the number */
		protected JTextField tf;
		/** The OK button */
		protected Button ok;
		/** The cancel button */
		protected Button can;

		/** Construct a GoToPage window (no actions yet) */
		public GoToPage(String title) {
			setTitle(title);
			Container cp = getContentPane();

			Label l = new Label("Quadrant number (hex):");
			tf = new JTextField(4);
			tf.setText("1");
			// set the text initially selected so you can easily overtype it
			tf.selectAll();

			ok = new Button("OK");
			can = new Button("Cancel");
			can.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					setVisible(false);
				}
			});

			ActionListener doIt = new ActionListener() {
				public void actionPerformed(ActionEvent e) {

					int n = -1;
					try {
						n = getValue();
					} catch(IllegalArgumentException notANumber) {
						// handled below
					}
					if (n >= 0 && n <= 255) {
						gotoPage(startNum = n*QUADSIZE);
						setVisible(false);
					} else
						Toolkit.getDefaultToolkit().beep();
				}
			};
			ok.addActionListener(doIt);
			tf.addActionListener(doIt);

			Panel top = new Panel();
			top.add(l);
			top.add(tf);

			Panel bottom = new Panel();
			bottom.add(ok);
			bottom.add(can);

			cp.add(BorderLayout.NORTH, top);
			cp.add(BorderLayout.SOUTH, bottom);

			pack();
		}

		protected int getValue() {
			int i = Integer.parseInt(tf.getText(), 16);
			return i;
		}
	}
}
