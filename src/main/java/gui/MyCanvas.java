package gui;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JLabel;

/**
 * Dummy Canvas class, to represent the real main part of an application *
 * Just subclasses JLabel.
 * @author Ian Darwin, http://www.darwinsys.com
 */

public class MyCanvas extends JLabel {
	protected int width, height;
	protected int pad;

	MyCanvas(int w, int h) {
		this("", w, h);
	}

	MyCanvas(String l, int w, int h) {
		super(l, JLabel.CENTER);
		width = w; height = h;
	}
	MyCanvas(String l, int w, int h, Color c) {
		this(l, w, h);
		setBackground(c);
	}

	public Dimension getPreferredSize() {
		return new Dimension(width+pad, height+pad);
	}
}
