package beans;

import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
import java.awt.TextArea;
import java.io.Serializable;

/**
 * Create a Text Insert from a file.
 */ 
public class CodeBean extends TextArea implements Serializable {
	
	private static final long serialVersionUID = -1749569826602688081L;
	protected String fileName;
	protected Font myFont = null;

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
