import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

import com.darwinsys.swingui.UtilGUI;

/** Prototype for Window Manager chooser. 
 * Recode in Qt for speed, someday.
 */
public class XwmChooser {
	JFrame jf;
	JButton apply;
	JButton cancel;
	JComboBox choice;

	public static void main(String[] args) {
		new XwmChooser();
	}

	public class WM {
		String description;
		String program;
		public WM(String d, String p) {
			description = d;
			program = p;
		}
		public String getDescription() {
			return description;
		}
		public String getProgram() {
			return program;
		}
		public String toString() {
			return description;
		}
	}

	public XwmChooser() {
		jf = new JFrame("Choose a Window Mangler");
		Container cp = jf.getContentPane();
		choice  = new JComboBox();
		choice.addItem(new WM("K Desktop 2.0 (KDE)", "/usr/local/bin/startkde"));
		choice.addItem(new WM("OPEN LOOK (olvwm)", "/usr/local/bin/olvwm"));
		choice.addItem(new WM("fvwm", "/usr/X11R6/bin/fvwm"));
		choice.addItem(new WM("xfwm", "/usr/local/bin/xfwm"));

		cp.add(BorderLayout.CENTER, choice);

		JPanel bottom = new JPanel();
		bottom.add(apply = new JButton("Apply"));
		apply.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				int n;
				if ((n = choice.getSelectedIndex()) == -1)
					return;
				WM wm = (WM)choice.getSelectedItem();
				String program = wm.getProgram();
				if (!new File(program).exists()) {
					JOptionPane.showMessageDialog(jf,
						"Program " + program + " does not seem to exist",
						"Whoa!", JOptionPane.ERROR_MESSAGE);
					return;
				}
				try {
					Runtime.getRuntime().exec(wm.program);
				} catch (IOException ex) {
					JOptionPane.showMessageDialog(jf,
						"Program " + program + " could not be run\n" + ex,
						"Whoa!", JOptionPane.ERROR_MESSAGE);
					return;
					
				}
			}
		});

		bottom.add(apply = new JButton("Cancel"));
		apply.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				System.exit(0);
			}
		});

		cp.add(BorderLayout.SOUTH, bottom);

		jf.pack();
		UtilGUI.center(jf);

		jf.setVisible(true);
	}
}
