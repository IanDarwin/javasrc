package gui.MVC;

import graphics.Grapher;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

/** MVC Implementation
 * @author	Ian Darwin, http://www.darwinsys.com/
 */
public class MVCDemo {

	/** "main program" method - construct and show */
	public static void main(String[] av) {

		// Create the data model
		final Model model = new Model();

		// create a JFrame to hold it all.
		final JFrame f = new JFrame("MVC");
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		Container cp = f.getContentPane();

		JPanel tp = new JPanel();
		cp.add(tp, BorderLayout.NORTH);

		tp.add(new JLabel("New value:"));
		final JTextField tf = new JTextField(10);
		tp.add(tf);
		tf.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				model.add(tf.getText());
				tf.setText("");
			}
		});

		JPanel p = new JPanel();

		// The first View is a JTextArea subclassed to have
		// an easy way of setting the data from a java.util.List
		final TextView tv = new TextView();
		model.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent evt) {
				Object o = evt.getSource();
				Model m = (Model) o;
				tv.setListData(m.getData());
			}
		});

		tv.setBackground(Color.RED);
		tv.setSize(100, 100);
		p.add(tv);

		// The second View is the simplistic Grapher program from
		// the Java Cookbook "Graphics" chapter (../graphics/Grapher.java)
		final Grapher vv = new Grapher();
		model.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent evt) {
				Object o = evt.getSource();
				Model m = (Model) o;
				vv.setListDataFromYStrings(m.getData());
			}
		});
		vv.setBackground(Color.YELLOW);
		vv.setSize(100, 100);
		p.add(vv);

		cp.add(p, BorderLayout.CENTER);

		f.pack();
		f.setLocation(100, 100);
		f.setVisible(true);
		
	}
}
