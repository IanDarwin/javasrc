package datetimeold;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.JOptionPane;

/**
 * Read a file of reminders, sleep until each is due, beep.
 * Much of this logic has been uperceded by java.util.Timer, which is used
 * in the non-Old version of this program.
 * @author Ian F. Darwin, http://www.darwinsys.com/
 */
public class ReminderServiceOld {
	static class Item {
		Date due;
		String message;
		Item(Date d, String m) {
			due = d;
			message = m;
		}
	}

	List<Item> l = new ArrayList<Item>();

	public static void main(String[] argv) throws IOException {
		ReminderServiceOld rs = new ReminderServiceOld();
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
		is.close();
	}

	public void run() {
		System.out.println("ReminderServiceOld: Starting at " + new Date());
		while (!l.isEmpty()) {
			Date d = new Date();
			Item i = (Item)l.get(0);
			long interval = i.due.getTime() - d.getTime();
			if (interval > 0) {
				System.out.println("Sleeping until " + i.due);
				try {
					Thread.sleep(interval);
				} catch (InterruptedException e) {
					return;	// unexpected intr
				}
				message(i.due + ": " + i.message);
			} else
				message("MISSED " + i.message + " at " + i.due);
			l.remove(0);
		}
	}
	void message(String message) {
		System.out.println("\007" + message);
		JOptionPane.showMessageDialog(null,
			message, 
			"Timer Alert",				// titlebar
			JOptionPane.INFORMATION_MESSAGE);	// icon
	}
}
