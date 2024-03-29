package gui;

import java.awt.Button;
import java.awt.GridLayout;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

/** Simple GridLayout demo program. */
public class GridLayDemo extends JPanel {
	private static final long serialVersionUID = 1L;
	
	public GridLayDemo() {
		Button b;
		setLayout(new GridLayout(2,3));
		add(b=new Button("B1"));
		b.addActionListener(al);
		add(b=new Button("B2"));
		b.addActionListener(al);
		add(b=new Button("B3"));
		b.addActionListener(al);
		add(b=new Button("C1"));
		b.addActionListener(al);
		add(b=new Button("C2"));
		b.addActionListener(al);
		add(b=new Button("C3"));
		b.addActionListener(al);
	}
	ActionListener al = ev -> {
		System.out.println("You pressed " + ev.getActionCommand());
	};

	public static void main(String[] args) {
		JFrame jf = new JFrame("GridLayDemo");
		jf.add(new GridLayDemo());
		jf.setBounds(100, 100, 200, 200);
		jf.setVisible(true);
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
