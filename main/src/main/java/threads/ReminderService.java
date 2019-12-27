package threads;

import java.nio.file.Files;
import java.nio.file.Path;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Read a file of reminders, run each when due using java.util.Timer
 * Input format is like:
 * 2021 12 25 10 30 Get some sleep.
 * Uses legacy java.util.Date because that's what TimerService needs
 */
// tag::main[]
public class ReminderService {

	/** The Timer object */
	Timer timer = new Timer();

	class Item extends TimerTask {
		String message;
		Item(String m) {
			message = m;
		}
		public void run() {
			message(message);
		}
	}

	public static void main(String[] argv) throws Exception {
		new ReminderService().loadReminders();
	}

	private String dfPattern = "yyyy MM dd hh mm ss";
	private SimpleDateFormat formatter = new SimpleDateFormat(dfPattern);

	protected void loadReminders() throws Exception {

		Files.lines(Path.of("ReminderService.txt")).forEach(aLine -> {

			ParsePosition pp = new ParsePosition(0);
			Date date = formatter.parse(aLine, pp);
			String task = aLine.substring(pp.getIndex());
			if (date == null) {
				System.out.println("Invalid date in " + aLine);
				return;
			}
			System.out.println("Date = " + date + "; task = " + task);
			timer.schedule(new Item(task), date);
		});
	}
	// end::main[]

	/** Display a message on the console and in the GUI.
	 * Used both by Item tasks and by mainline parser.
	 */
	void message(String message) {
		System.out.println("\007" + message);
	}
}
