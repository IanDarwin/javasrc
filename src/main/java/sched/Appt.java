import java.util.*;

/** Simple Appointment object, to represent a year-month-day-hour-minute.
 * @author Ian Darwin
 * @version $Id$
 */
public class Appt implements Comparable {


	//-----------------------------------------------------------------
	//	MAIN CLASS VARIABLES -- APPOINTMENT
	//-----------------------------------------------------------------
	/** What we have to do at this time. */
	String target;	
	/** How often it recurs -- MAY BE NULL */
	Repeat when;
	/** The year (Gregorian calendar) when the appointment is */
	int year;
	/** The month (0-origin) */
	int month;
	/** The day */
	int day;
	/** The hour */
	int hour;
	/** The minute */
	int minute;
	//-----------------------------------------------------------------
	//	CONSTANTS FOR REPETITIONS
	//-----------------------------------------------------------------
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
	/** The month repetition meaning "The 12th of every month" */
	public static final int MONTHLY_NUMDAY_OF_M = 41;
	/** The month repetition meaning "The 2nd Tuesday of every month" */
	public static final int MONTHLY_WEEKDAY_OF_M = 42;
	/** The repetition type for no repetition */
	public static final int YEARLY = 5;

	//-----------------------------------------------------------------
	//	MAIN CLASS VARIABLES -- REPETITION
	//-----------------------------------------------------------------
	/** The repetition type for this Repeat object */
	protected int type = NONE;
	/** The interval: 2=every other (hour, day, month, year) */
	protected int r_interval = NONE;
	/** The number of times to repeat this event */
	protected int r_count = NONE;

	//-----------------------------------------------------------------
	//	METHODS - CONSTRUCTOR(S)
	//-----------------------------------------------------------------
	/** Construct an Appointment. */
	public Appt(String targ, int y, int mo, int d, int h, int min) {
		target = targ;
		year = y;
		month  = mo;
		day  = d;
		hour = h;
		minute = min;
	}

	//-----------------------------------------------------------------
	//	METHODS - REPETITION
	//-----------------------------------------------------------------
	public boolean matches(int y, int m, int d) {
		// Do the simple case first!
		if (year == y && month == m && day == d)
			return true;
		// XXX consider using a GregorianCalendar for calculations
		// from here on in...
		// System.out.println("ME:"+year+","+month+","+day);
		// System.out.println("YE:"+y   +","+m    +","+d  );

		return false;
	}

	//-----------------------------------------------------------------
	//	METHODS - COMPARISON
	//-----------------------------------------------------------------
	/** Construct an Appointment. */
	/** compareTo method, from Comparable interface.
	 * Compare this Appointment against another, for purposes of sorting.
	 * <P>Only date and time participate, not repetition!
	 * Consistent with equals().
	 * @return -1 if this<a2, +1 if this>a2, else 0.
	 */ 
	public int compareTo(Object o2) {
		Appt a2 = (Appt) o2;
		if (year < a2.year)
			return -1;
		if (year > a2.year)
			return +1;
		if (month < a2.month)
			return -1;
		if (month > a2.month)
			return +1;
		if (day < a2.day)
			return -1;
		if (day > a2.day)
			return +1;
		if (hour < a2.hour)
			return -1;
		if (hour > a2.hour)
			return +1;
		if (minute < a2.minute)
			return -1;
		if (minute > a2.minute)
			return +1;
		return target.compareTo(a2.target);
	}

	/** Compare this appointment against another, for equality.
	 * Consistent with compareTo(). For this reason, only
	 * date & time participate, not repetition.
	 * @returns true if the objects are equal, false if not.
	 */
	public boolean equals(Object o2) {
		Appt a2 = (Appt) o2;
		if (year != a2.year ||
			month != a2.month ||
			day != a2.day ||
			hour != a2.hour ||
			minute != a2.minute)
			return false;
		return target.equals(a2.target);
	}

	/** Return a String representation of this Appt.
	 * Output is intended for debugging, not presentation!
	 */
	public String toString() {
		return new StringBuffer().append(year).append(' ').
			append(month).append(' ').append(day).append(' ').
			append(hour).append(' ').append(minute).append(' ').
			append(target).toString();
	}

	/** Factory: build a String representation into an Appt.
	 * Cast as a static factory for minor efficiency gains - reconsider.
	 */
	public static Appt fromString(String s) {
		StringTokenizer st = new StringTokenizer(s);
		if (st.countTokens() < 6) throw new
			IllegalArgumentException("Too few fields in " + s);
		int y = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		int d = Integer.parseInt(st.nextToken());
		int h = Integer.parseInt(st.nextToken());
		int i = Integer.parseInt(st.nextToken());
		StringBuffer sb = new StringBuffer();
		while (st.hasMoreElements()) {
			sb.append(st.nextToken());
			if (st./*still*/hasMoreElements())
				sb.append(' ');
		}
		return new Appt(sb.toString(), y, m, d, h, i);
	}
}
