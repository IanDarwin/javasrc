import java.io.*;
import java.util.*;
import java.text.*;

public class CalEventMaker {

	static int nEvent;
	static Random r = new Random();

	public static void main(String[] args) throws IOException {
		String TMPFILE = "j.ics";
		PrintWriter out = new PrintWriter(new FileWriter(TMPFILE));
		writeEvent(out, args[0], 
			Integer.parseInt(args[1]),
			Integer.parseInt(args[2]),
			Integer.parseInt(args[3]),
			Integer.parseInt(args[4]));
		out.close();
		Runtime.getRuntime().exec("open" + ' ' + TMPFILE);
	}
	
	public static void writeEvent(PrintWriter out, String event,
			int startMon, int startDay,
			int endMon, int endDay)
	{
		out.println("BEGIN:VCALENDAR");
		out.println("CALSCALE:GREGORIAN");
		out.println("X-WR-TIMEZONE;VALUE=TEXT:Canada/Eastern");
		out.println("METHOD:PUBLISH");
		out.println("PRODID:-//Darwin Open Systems//CalEventMaker 1.0//EN");
		out.println("X-WR-CALNAME;VALUE=TEXT:Work");
		out.println("VERSION:2.0");
		out.println("BEGIN:VEVENT");
		out.println("SEQUENCE:" + nEvent++);
		out.println("UID:27F0307F-37A6-21D7-AD6A-" + r.nextInt(9999999));
		out.println("SUMMARY:" + event);
		out.println("STATUS:TENTATIVE");
		out.println("DTSTART;VALUE=DATE:" + int4digit(2003) + int2digit(startMon) + int2digit(startDay));
		out.println("DTEND;VALUE=DATE:" + int4digit(2003) + int2digit(endMon) + int2digit(endDay));
		out.println("END:VEVENT");
		out.println("END:VCALENDAR");
	}

	private static NumberFormat format4d = new DecimalFormat("0000");
	public static String int4digit(int yyyy) {
		return format4d.format(yyyy);
	}

	private static NumberFormat format2d = new DecimalFormat("00");
	public static String int2digit(int mm) {
		return format2d.format(mm);
	}
}
