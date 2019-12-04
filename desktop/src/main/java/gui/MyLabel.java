package gui;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JLabel;

/**
 * Dummy Canvas class, to represent the real main part of an application *
 *
 * @author Ian Darwin, http://www.darwinsys.com
 */

class MyLabel extends JLabel {
	int width, height;
	int pad;

	MyLabel(int w, int h) {
		this("", w, h);
	}
	MyLabel(String l, int w, int h) {
		super(l, JLabel.CENTER);
		width = w; height = h;
	}
	MyLabel(String l, int w, int h, Color c) {
		this(l, w, h);
		setBackground(c);
	}

	public Dimension getMinimumSize() {
		return new Dimension(width, height);
	}

	public Dimension getPreferredSize() {
		return new Dimension(width+pad, height+pad);
	}
}
