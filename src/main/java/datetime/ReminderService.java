import java.io.*;
import java.text.*;
import java.util.*;
import javax.swing.*;

/**
 * Read a file of reminders, sleep until each is due, beep.
 * @author Ian F. Darwin, ian@darwinsys.com
 * @version $Id$
 */
public class ReminderService {
	class Item {
		Date due;
		String message;
		Item(Date d, String m) {
			due = d;
			message = m;
		}
	}

	ArrayList l = new ArrayList();

	public static void main(String[] argv) throws IOException {
		ReminderService rs = new ReminderService();
		rs.load();
		rs.run();
	}

	protected void load() throws IOException {

		BufferedReader is = new BufferedReader(
			new FileReader("ReminderService.txt"));
		SimpleDateFormat formatter =
			new SimpleDateFormat ("yyyy MM dd hh mm");
		String aLine;
		while ((aLine = is.readLine()) != null) {
			ParsePosition pp = new ParsePosition(0);
			Date date = formatter.parse(aLine, pp);
			if (date == null) {
				message("Invalid date in " + aLine);
				continue;
			}
			String mesg = aLine.substring(pp.getIndex());
			l.add(new Item(date, mesg));
		}
	}

	public void run() {
		System.out.println("ReminderService: Starting at " + new Date());
		while (!l.isEmpty()) {
			Date d = new Date();
			Item i = (Item)l.get(0);
			long interval = i.due.getTime() - d.getTime();
			if (interval > 0) {
				System.out.println("Sleeping until " + i.due);
				try {
					Thread.sleep(interval);
				} catch (InterruptedException e) {
					System.exit(1);	// unexpected intr
				}
				message(i.due + ": " + i.message);
			} else
				message("MISSED " + i.message + " at " + i.due);
			l.remove(0);
		}
		System.exit(0);
	}
	void message(String message) {
		System.out.println("\007" + message);
		JOptionPane.showMessageDialog(null,
			message, 
			"Timer Alert",				// titlebar
			JOptionPane.INFORMATION_MESSAGE);	// icon
	}
}
