import java.util.*;

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

	public static void main(String argv[]) {
		ReminderService rs = new ReminderService();
		rs.load();
		rs.run();
	}

	protected void load() {
		l.add(new Item(new Date(1999, 07, 17, 20, 59), "Item one"));
		l.add(new Item(new Date(1999, 07, 17, 21, 02), "Item Two"));
	}

	public void run() {
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
				System.out.println("\007" + i.message);
			}
			l.remove(0);
		}
	}
}
