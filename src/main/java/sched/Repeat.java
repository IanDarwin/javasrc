package sched;

/** A Repeat is a representation of when a repeating appointment.
 * TODO think about codes for recurring appointments;
 * check out existing programs, and Sun's RPC CMSD values
 * like each/every etc....
 */
public class Repeat {
	/** A constant for fields that do not take part in a comparison. */
	public static final int N_A = -1;
	/** The repetition type for no repetition */
	public static final int NONE = 0;
	/** The repetition type for no repetition */
	public static final int HOURLY = 1;
	/** The repetition type for no repetition */
	public static final int DAILY = 2;
	/** The repetition type for no repetition */
	public static final int WEEKLY = 3;
	/** The repetition type for no repetition */
	public static final int MONTHLY = 4;
	/** The repetition type for no repetition */
	public static final int YEARLY = 5;
	/** The repetition type for this Repeat object */
	protected int type = NONE;
	/** The interval: 2=every other (hour, day, month, year) */
	protected int interval = 0;
	/** The number of times to repeat this event */
	protected int count = 0;
	/** The month repetition meaning "The 12th of every month" */
	public static final int NUMDAY_OF_M = 24;
	/** The month repetition meaning "The 2nd Tuesday of every month" */
	public static final int WEEKDAY_OF_M = 42;
}
