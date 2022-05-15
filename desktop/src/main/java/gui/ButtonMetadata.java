package gui;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

public class ButtonMetadata {
	static final int SIZE = 4;
	static record Info(int x, int y, char ch){};
	static Map<JButton, Info> data = new HashMap<>();
	final static char[] LETTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
	public static void main(String[] args) {
		System.out.println("Start");
		JFrame jf = new JFrame("ButtonMetadata");
		jf.setLocation(100,100);
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JPanel c = new JPanel();
		c.setLayout(new GridLayout(4,4));
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				char ch = LETTERS[i*SIZE + j];
				JButton b = new JButton("" + ch);
				b.addMouseListener(bakka);
				data.put(b, new Info(i, j, ch));
				c.add(b);
			}
		}
		jf.add(c);
		jf.setSize(200, 200);
		jf.setVisible(true);
	}
	static MouseAdapter bakka = new MouseAdapter() {
		public void mouseClicked(MouseEvent e) {
			System.out.println("Click " + data.get(e.getComponent()));
		}
		public void mouseEntered(MouseEvent e) {
			System.out.println("Entered " + data.get(e.getComponent()));
		}
	};
}
