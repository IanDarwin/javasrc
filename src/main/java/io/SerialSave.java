import java.io.*;
import java.util.*;
import javax.swing.*;

/** Show Object Serialization saving a file.
 */
public class SerialSave extends JFrame {
	/** The list of user objects to be saved */
	protected Vector usrList = new Vector();
	/** The JList used to display the users */
	protected JList visList = new JList();
	/** The filename */
	protected final String FILENAME = "users.ser";
	protected final String BACKFILENAME = FILENAME + ".bak";

	/**
	 * Save (serialize) current list to a file. Java 1.1's 
	 * Object Persistence does all the work; we just ask it
	 * to save the entire User List (a Vector), and an Integer
	 * which represents the currently-selected user, if any.
	 */
	protected void save() {
		if (usrList.size() == 0) {
			JOptionPane.showMessageDialog(this, 
				"Nothing to save",
				"No records to save! Save ignored",
				JOptionPane.WARNING_MESSAGE);
			return;
		}
		try {
			File f = new File(FILENAME);
			if (f.exists()) {
				String fn = FILENAME, bfn = BACKFILENAME;
				File bk = new File(bfn);
				if (bk.exists()) {
					// println("Deleting " + bk.getName());
					if (!bk.delete())
						throw new IOException("Delete of " + bk.getName() + " failed!");
				}
				if (!f.renameTo(bk))
					throw new IOException("Rename of " + f.getName() + " to " + bk.getName() + " failed!");
			}
			ObjectOutputStream s = new ObjectOutputStream(
				new FileOutputStream(FILENAME));
			s.writeObject(usrList);
			s.writeObject(new Integer(visList.getSelectedIndex()));
			s.flush();
			s.close();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, 
				"I/O Error", "I/O Error Saving: " + e,
				JOptionPane.ERROR_MESSAGE);
		}
	}
}
