import javax.swing.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;

/** A simple demo of a JFileChooser in action. */
public class JFileChooserDemo extends JPanel {

	/** Constructor */
	public JFileChooserDemo(JFrame f) {
		final JFrame frame = f;
		final JFileChooser chooser = new JFileChooser();
		JFileFilter filter = new JFileFilter();
		filter.addType("java");
		filter.addType("class");
		filter.addType("jar");
		filter.setDescription("Java-related files");
		chooser.addChoosableFileFilter(filter);
		JButton b = new JButton("Choose file...");
		add(b);
		b.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			int returnVal = chooser.showOpenDialog(frame);
			if (returnVal == JFileChooser.APPROVE_OPTION) {
				System.out.println("You chose a file named: " + 
					chooser.getSelectedFile().getPath());
			} else {
				System.out.println("You did not choose a file.");
			}
			}
		});
	}


	public static void main(String[] args) {
		JFrame f = new JFrame("JFileChooser Demo");
		f.getContentPane().add(new JFileChooserDemo(f));
		f.pack();
		f.setVisible(true);
		f.addWindowListener(new WindowCloser(f, true));
	}
}
