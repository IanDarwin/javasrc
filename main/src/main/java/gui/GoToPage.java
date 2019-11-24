package gui;

import java.awt.BorderLayout;
import java.awt.Dialog;
import java.awt.Label;
import java.awt.Panel;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

/** Implement a simple "Go To Page" dialog
 * Row one: "Go to Page", textfield
 * second OK, Cancel buttons.
 */
public class GoToPage extends Dialog {
	/** TextField used to enter the number */
	protected TextField tf;
	/** The OK button */
	protected JButton ok;
	/** The cancel button */
	protected JButton can;

	/** Construct a GoToPage window (no actions yet) */
	public GoToPage(JFrame f, String title) {
		super(f);
		setTitle(title);

		Label l = new Label("Page Number:");
		tf = new TextField(4);
		tf.setText("1");
		// set the text initially selected so you can easily overtype it
		tf.selectAll();

		ok = new JButton("OK");
		can = new JButton("Cancel");

		Panel top = new Panel();
		top.add(l);
		top.add(tf);

		Panel bottom = new Panel();
		bottom.add(ok);
		bottom.add(can);

		add(BorderLayout.NORTH, top);
		add(BorderLayout.SOUTH, bottom);

		pack();
	}

	protected int getValue() {
		int i = Integer.parseInt(tf.getText());
		return i;
	}

	public static void main(String[] unused) {
		final JFrame f = new JFrame("Page Dialog Test");
		JButton b;
		f.getContentPane().add(b = new JButton("Show Dialog"));
		b.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new GoToPage(f, "GoToPage Demo").setVisible(true);
			}
		});
		f.pack();
		f.setVisible(true);
	}
}
