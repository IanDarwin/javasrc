package com.darwinsys.codebean;

import java.awt.*;
import java.io.*;

/**
 * Create a Text Insert from a file.
 */ 
public class CodeBean extends TextArea implements Serializable {
	protected String fileName;
	protected Font myFont;

	public String getFileName() {
		return fileName;
	}
	public void setFileName(String _fileName) {
		fileName = _fileName;
	}

	public CodeBean() {
		super("", 20, 80, SCROLLBARS_NONE);

		if (myFont == null) {
			myFont = new Font("Courier", Font.BOLD, 10);
		}
		setFont(myFont);
		setForeground(Color.red);
		setText("\nWould load " + fileName + "\nNOW.");
	}

	public static void main(String[] args) {
		Frame f = new Frame("CodeBean Demo");
		CodeBean cb = new CodeBean();
		cb.setFileName("TESTING 1 2 3");
		f.add(cb);
		f.pack();
		f.setVisible(true);
	}
}
