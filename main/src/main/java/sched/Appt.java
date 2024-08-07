package sched;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.StringTokenizer;

import javax.validation.constraints.NotNull;

/** 
 * Simple Appointment object, to represent a year-month-day-hour-minute.
 * Classes are semi-immutable: main fields immutable, repetition settable.
 * @author Ian Darwin
 */
public class Appt implements Comparable<Appt> {

	//-----------------------------------------------------------------
	//	MAIN CLASS VARIABLES -- APPOINTMENT
	//-----------------------------------------------------------------
	/** What we have to do at this time. */
	@NotNull
	final String text;	
	/** The day when the appointment is */
	@NotNull
	final LocalDate date;
	/** The time of day - optional for TODO-type items */
	final LocalTime time;
	//-----------------------------------------------------------------
	//	CONSTANTS FOR REPETITIONS
	//-----------------------------------------------------------------
	/** The repetition type for no repetition */
	public static final int NONE = 0;
	/** The repetition type for no repetition */
	public static final int HOURLY = 1;
	/** The repetition type for daily repetition */
	public static final int DAILY = 2;
	/** The repetition type for weekly repetition */
	public static final int WEEKLY = 3;
	/** The month repetition meaning "The 12th of every month" */
	public static final int MONTHLY_NUMDAY_OF_M = 41;
	/** The month repetition meaning "The 2nd Tuesday of every month" */
	public static final int MONTHLY_WEEKDAY_OF_M = 42;
	/** The repetition type for yearly repetition */
	public static final int YEARLY = 5;
	/** The count factor meaning forever */
	public static final int FOREVER = Integer.MAX_VALUE;

	//-----------------------------------------------------------------
	//	MAIN CLASS VARIABLES -- REPETITION
	//-----------------------------------------------------------------
	/** The repetition type for this repeated object */
	protected int r_type = NONE;
	/** The interval: 2=every other (hour, day, month, year) */
	protected int r_interval = NONE;
	/** The number of times to repeat this event */
	protected int r_count = NONE;

	//-----------------------------------------------------------------
	//	METHODS - CONSTRUCTOR(S)
	//-----------------------------------------------------------------
	/** Construct an Appointment. */
	public Appt(int y, int mo, int d, int h, int min, String text) {
		this.text = text;
		date = LocalDate.of(y, mo, d);
		time = LocalTime.of(h, min);
	}
	
	public Appt(LocalDateTime datetime, String text) {
		this.text = text;
		this.date = datetime.toLocalDate();
		this.time = datetime.toLocalTime();
	}
	
	public Appt(LocalDate date, LocalTime time, String text) {
		this.text = text;
		this.date = date;
		this.time = time;
	}

	//-----------------------------------------------------------------
	//	METHODS - REPETITION
	//-----------------------------------------------------------------
	public void setRep(int typ, int intv, int count) {
		r_type = typ;
		r_interval = intv;
		r_count = count;
	}

	/** Decide whether a given Appointment matches the given y/m/d.
	 */
	public boolean matches(int y, int m, int d) {
		// Do the simple case first!
		LocalDate dt = LocalDate.of(y, m, d);
		if (date.equals(dt))
			return true;
		// If NOT today AND no repetition, not interesting.
		if (r_count == NONE)
			return false;

		// Else potentially interesting!
		LocalDate newDay = date;

		for (int i=0; i<r_count && date.compareTo(dt) < 0; i++) {
			switch(r_type) {
			case HOURLY:
				break;
			case DAILY:
				newDay = date.plusDays(r_interval);
				break;
			case WEEKLY:
				break;
			case MONTHLY_NUMDAY_OF_M:
				break;
			case MONTHLY_WEEKDAY_OF_M:
				break;
			case YEARLY:
				break;
			}

			// OK, we did the increment. Now see if it
			// matches the date we're looking for.
			if (newDay.equals(date))
				return true;
		}

		// We got out of the loop without finding a match, so...
		return false;
	}

	// tag::main[]
// public class Appt implements Comparable {
	// Much code and variables omitted - see online version
	//-----------------------------------------------------------------
	//	METHODS - COMPARISON
	//-----------------------------------------------------------------
	/** compareTo method, from Comparable interface.
	 * Compare this Appointment against another, for purposes of sorting.
	 * <P>Only date and time, then text, participate, not repetition!
	 * (Repetition has to do with recurring events, e.g., 
	 * "Meeting every Tuesday at 9").
	 * This method is consistent with equals().
	 * @return -1 if this<a2, +1 if this>a2, else 0.
	 */
	@Override
	public int compareTo(Appt a2) {
		// If dates not same, trigger on their comparison
		int dateComp = date.compareTo(a2.date);
		if (dateComp != 0)
			return dateComp;
		// Same date. If times not same, trigger on their comparison
		if (time != null && a2.time != null) {
			// Neither time is null
			int timeComp = time.compareTo(a2.time);
			if (timeComp != 0)
				return timeComp;
		} else /* At least one time is null */ {
			if (time == null && a2.time != null) {
				return -1; // All-day appts sort low to appear first
			} else if (time != null && a2.time == null)
				return +1;
				// else both have no time set, so carry on,
		}
		// Same date & time, trigger on text
		return text.compareTo(a2.text);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		result = prime * result + ((text == null) ? 0 : text.hashCode());
		result = prime * result + ((time == null) ? 0 : time.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object o2) {
		if (this == o2)
			return true;
		if (o2.getClass() != Appt.class)
			return false;
		Appt a2 = (Appt) o2;
		if (!date.equals(a2.date))
			return false;
		if (time != null && !time.equals(a2.time))
			return false;
		return text.equals(a2.text);
	}

	/** Return a String representation of this Appt.
	 * Output is intended for debugging, not presentation!
	 */
	@Override
	public String toString() {
		var sb = new StringBuilder();
		sb.append(date).append(' ');
		if (time != null) {
			sb.append(time.getHour())
			.append(':')
			.append(time.getMinute())
			.append(' ');
		} else {
			sb.append("(All day)").append(' ');
		}
		sb.append(text).toString();
		return sb.toString();
	}
	// end::main[]

	/** Factory: build a String representation into an Appt.
	 * Cast as a static factory for minor efficiency gains - reconsider.
	 */
	public static Appt fromString(String s) {
		System.out.println("Appt.fromString(): " + s);
		// Tokenize, after stripping '-' and ':'.
		StringTokenizer st = new StringTokenizer(s.replaceAll("[-:]", " "));
		if (st.countTokens() < 6) throw new
			IllegalArgumentException("Too few fields in " + s);
		int y = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		int d = Integer.parseInt(st.nextToken());
		int h = Integer.parseInt(st.nextToken());
		int i = Integer.parseInt(st.nextToken());
		StringBuilder sb = new StringBuilder();
		while (st.hasMoreElements()) {
			sb.append(st.nextToken());
			if (st./*still*/hasMoreElements())
				sb.append(' ');
		}
		return new Appt(y, m, d, h, i, sb.toString());
	}

	public int getDay() {
		return date.getDayOfMonth();
	}

	public int getHour() {
		return time.getHour();
	}

	public int getMinute() {
		return time.getMinute();
	}

	public int getMonth() {
		return date.getMonthValue();
	}
	
	public String getText() {
		return text;
	}
	
	public int getYear() {
		return date.getYear();
	}
	
}
