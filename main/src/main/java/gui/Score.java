package gui;

import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.text.DecimalFormat;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JTextField;

/** Class to display and update a Scoreboard for a Quiz game.
 */
public class Score {
	protected JFrame f;
	/** The left text field */
	JTextField lst;
	/** The right-hand text field */
	JTextField rst;
	/** A formatter for all the numbers */
	DecimalFormat df = new DecimalFormat("000");

	/** Construct the GUI */
	Score() {
		JButton b;
		f = new JFrame("Score");

		JMenuBar mb = new JMenuBar();
		JMenuItem mi;

		JMenu fm = new JMenu("File");
		fm.add(mi = new JMenuItem("Quit"));
		mi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				System.exit(0);
			}
		});
		mb.add(fm);

		JMenu em = new JMenu("Edit");
		em.add(mi = new JMenuItem("Reset"));
		mi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				lst.setText(df.format(0));
				rst.setText(df.format(0));
			}
		});
		mb.add(em);
		f.setJMenuBar(mb);

		Container cp = f.getContentPane();
		cp.setLayout(new FlowLayout());
		Font bigF = new Font("helvetica", Font.BOLD, 128);
		cp.add(lst = new JTextField(df.format(0)));
		lst.setFont(bigF);

		cp.add(b = new JButton("+"));
		b.addActionListener(new Adder(lst, 1));

		cp.add(rst = new JTextField(df.format(0)));
		rst.setFont(bigF);

		cp.add(b = new JButton("+"));
		b.addActionListener(new Adder(rst, 1));

		f.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				//System.out.println(e);
				switch(e.getKeyChar()) {
					case 'l': case 'L': incr(lst); break;
					case 'r': case 'R': incr(rst); break;
					// XXX "Q" should require a JOptionPane confirmation...
					case 'Q': System.exit(0); break;
				} 
			}
		});

		f.pack();
	}

	/** Little convenience routine, so we can be used like JFrame */
	public void setVisible(boolean b) {
		f.setVisible(b);
	}

	/** Inner class to update one score by one amount. */
	class Adder implements ActionListener {
		JTextField textfield;
		int increment;
		public Adder(JTextField tf, int incr) {
			textfield = tf;
			increment = incr;
		}
		public void actionPerformed(ActionEvent e) {
			incr(textfield, increment);
		}
	}

	protected void incr(JTextField j) {
		incr(j, 1);
	}

	protected void incr(JTextField j, int incr) {
		String t = j.getText().trim();
		int i = 0;
		try {
			i = Integer.parseInt(t);
		} catch (NumberFormatException e) {
			System.out.println("BLATZ");
		}
		j.setText(df.format(i + incr));
	}

	public static void main(String[] args) {
		new Score().setVisible(true);
	}
}
