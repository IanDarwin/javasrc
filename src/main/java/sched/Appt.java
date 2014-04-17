package sched;

import java.util.*;

/** Simple Appointment object, to represent a year-month-day-hour-minute.
 * @author Ian Darwin
 */
public class Appt implements Comparable<Appt> {

    //-----------------------------------------------------------------
    //    MAIN CLASS VARIABLES -- APPOINTMENT
    //-----------------------------------------------------------------
    /** What we have to do at this time. */
    String text;    
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
    //    CONSTANTS FOR REPETITIONS
    //-----------------------------------------------------------------
    /** A constant for fields that do not take part in a comparison. */
    public static final int N_A = -1;
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
    //    MAIN CLASS VARIABLES -- REPETITION
    //-----------------------------------------------------------------
    /** The repetition type for this repeated object */
    protected int r_type = NONE;
    /** The interval: 2=every other (hour, day, month, year) */
    protected int r_interval = NONE;
    /** The number of times to repeat this event */
    protected int r_count = NONE;
    /** The Calendar object used for date calculations. */
    protected static GregorianCalendar gc;

    //-----------------------------------------------------------------
    //    METHODS - CONSTRUCTOR(S)
    //-----------------------------------------------------------------
    /** Construct an Appointment. */
    public Appt(String text, int y, int mo, int d, int h, int min) {
        this.text = text;
        year = y;
        month  = mo;
        day  = d;
        hour = h;
        minute = min;
        if (gc == null)
            gc = new GregorianCalendar();
    }

    //-----------------------------------------------------------------
    //    METHODS - REPETITION
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
        if (year == y && month == m && day == d)
            return true;
        // If NOT today AND no repetition, not interesting.
        if (r_count == NONE)
            return false;

        // Else potentially interesting!

        // System.out.println("ME:"+year+","+month+","+day);
        // System.out.println("YE:"+y   +","+m    +","+d  );

        // using our GregorianCalendar for calculations from here on... 
        gc.set(Calendar.YEAR,  year);
        gc.set(Calendar.MONTH, month-1);
        gc.set(Calendar.DAY_OF_MONTH, day);
        gc.set(Calendar.HOUR,  hour);
        gc.set(Calendar.MINUTE, minute);

        System.out.println(gc.getTime().toString());

        for (int i=0; i<r_count && 
            gc.get(Calendar.YEAR)<=year && 
            gc.get(Calendar.MONTH)<=month && 
            gc.get(Calendar.DAY_OF_MONTH)<=day; i++) {
            switch(r_type) {
            case HOURLY:
                break;
            case DAILY:
                gc.add(Calendar.DAY_OF_MONTH, r_interval);
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
            if (gc.get(Calendar.YEAR) == y &&
                gc.get(Calendar.MONTH) == m &&
                gc.get(Calendar.DAY_OF_MONTH) == d)
                return true;
        } 

        // We got out of the loop without finding a match, so...
        return false;
    }

    // BEGIN main
// public class Appt implements Comparable {
    // Much code and variables omitted - see online version
    //-----------------------------------------------------------------
    //    METHODS - COMPARISON
    //-----------------------------------------------------------------
    /** compareTo method, from Comparable interface.
     * Compare this Appointment against another, for purposes of sorting.
     * <P>Only text, and date and time participate, not repetition!
     * (Repetition has to do with recurring events, e.g., 
	 *  "Meeting every Tuesday at 9").
     * This methods is consistent with equals().
     * @return -1 if this<a2, +1 if this>a2, else 0.
     */
    @Override
    public int compareTo(Appt a2) {
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
        return text.compareTo(a2.text);
    }

    /** Compare this appointment against another, for equality.
     * Consistent with compareTo(). For this reason, only
     * text, date & time participate, not repetition.
     * @returns true if the objects are equal, false if not.
     */
    @Override
    public boolean equals(Object o2) {
        Appt a2 = (Appt) o2;
        if (year != a2.year ||
            month != a2.month ||
            day != a2.day ||
            hour != a2.hour ||
            minute != a2.minute)
            return false;
        return text.equals(a2.text);
    }
    // END main

    /** Return a String representation of this Appt.
     * Output is intended for debugging, not presentation!
     */
    @Override
    public String toString() {
        return new StringBuffer().append(year).append(' ').
            append(month).append(' ').append(day).append(' ').
            append(hour).append(' ').append(minute).append(' ').
            append(text).toString();
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

    public int getDay() {
        return day;
    }

    public int getHour() {
        return hour;
    }

    public int getMinute() {
        return minute;
    }

    public int getMonth() {
        return month;
    }
    
    public String getText() {
        return text;
    }
    
    public int getYear() {
        return year;
    }
    
}
