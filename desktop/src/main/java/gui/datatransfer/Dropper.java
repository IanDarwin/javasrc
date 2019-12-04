package gui.datatransfer;

import java.awt.Toolkit;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.TransferHandler;

/**
 * Dropper - show File Drop Target from Drag-n-Drop
 */
public class Dropper extends JFrame {

	private static final long serialVersionUID = 4609632168673792774L;

	/**
	 * Construct trivial GUI and connect a TransferHandler to it.
	 */
	public Dropper() {
		super("Drop Target");

		JComponent cp = (JComponent) getContentPane();
		cp.setTransferHandler(new MyFileTransferHandler()); // see below

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(150, 150);
	}

	/** Instantiate and show the GUI */
	public static void main(String[] args) {
		new Dropper().setVisible(true);
	}
}

/** Non-public class to handle filename drops */
class MyFileTransferHandler extends TransferHandler {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * @see javax.swing.TransferHandler#canImport(javax.swing.JComponent,
	 *      java.awt.datatransfer.DataFlavor[])
	 */
	public boolean canImport(JComponent arg0, DataFlavor[] arg1) {
		for (int i = 0; i < arg1.length; i++) {
			DataFlavor flavor = arg1[i];
			if (flavor.equals(DataFlavor.javaFileListFlavor)) { 
				System.out.println("canImport: JavaFileList FLAVOR: " + flavor);
				return true;
			}
			if (flavor.equals(DataFlavor.stringFlavor)) { 
				System.out.println("canImport: String FLAVOR: " + flavor);
				return true;
			}
			System.err.println("canImport: Rejected Flavor: " + flavor);
		}
		// Didn't find any that match, so:
		return false;
	}

	/**
	 * Do the actual import.
	 * 
	 * @see javax.swing.TransferHandler#importData(javax.swing.JComponent,
	 *      java.awt.datatransfer.Transferable)
	 */
	public boolean importData(JComponent comp, Transferable t) {
		DataFlavor[] flavors = t.getTransferDataFlavors();
		System.out.println("Trying to import:" + t);
		System.out.println("... which has " + flavors.length + " flavors.");
		for (int i = 0; i < flavors.length; i++) {
			DataFlavor flavor = flavors[i];
			try {
				if (flavor.equals(DataFlavor.javaFileListFlavor)) {
					System.out.println("importData: FileListFlavor");

					@SuppressWarnings("unchecked")
					List<File> listOfFiles = 
						(List<File>) t.getTransferData(DataFlavor.javaFileListFlavor);
					for (File file : listOfFiles) {
						System.out.println("GOT FILE: " + file.getCanonicalPath());
						// Now do something with the file...
					}
					return true;
				} else if (flavor.equals(DataFlavor.stringFlavor)) {
					System.out.println("importData: String Flavor");
					String fileOrURL = (String)t.getTransferData(flavor);
					System.out.println("GOT STRING: " + fileOrURL);
					try {
						URL url = new URL(fileOrURL);
						System.out.println("Valid URL: " + url.toString());
						// Do something with the contents...
						return true;
					} catch (MalformedURLException ex) {
						System.err.println("Not a valid URL");
						return false;
					}
					// now do something with the String.

				} else {
					System.out.println("importData rejected: " + flavor);
					// Don't return; try next flavor.
				}
			} catch (IOException ex) {
				System.err.println("IOError getting data: " + ex);
			} catch (UnsupportedFlavorException e) {
				System.err.println("Unsupported Flavor: " + e);
			}
		}
		// If you get here, I didn't like the flavor.
		Toolkit.getDefaultToolkit().beep();
		return false;
	}
}
