package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;

public class StyledText {
	public static void main(String args[]) throws BadLocationException {
		JFrame jf = new JFrame("StyledText");
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container cp = jf.getContentPane();

		JTextPane pane = new JTextPane();
		SimpleAttributeSet set = new SimpleAttributeSet();
		StyleConstants.setBold(set, true);

		// Set the attributes before adding text
		pane.setCharacterAttributes(set, true);
		pane.setText("Eine ");

		set = new SimpleAttributeSet();
		StyleConstants.setItalic(set, true);
		StyleConstants.setForeground(set, Color.red);
		StyleConstants.setBackground(set, Color.blue);

		Document doc = pane.getStyledDocument();
		doc.insertString(doc.getLength(), "Kleine ", set);

		set = new SimpleAttributeSet();
		StyleConstants.setFontSize(set, 24);

		doc.insertString(doc.getLength(), "Nachtmusic", set);

		JScrollPane scrollPane = new JScrollPane(pane);
		cp.add(scrollPane, BorderLayout.CENTER);

		jf.setSize(400, 300);
		jf.setVisible(true);
	}
}

