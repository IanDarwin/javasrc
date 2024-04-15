package servlet;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Calendar;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/** Program to generate an iCalendar event.
 * Can be used as a main program or as a Servlet.
 */
public class CalEventMaker extends HttpServlet {

	static int nEvent;
	static Random r = new Random();

	public static void main(String[] args) throws IOException {
		String TMPFILE = "j.ics";
		PrintWriter out = new PrintWriter(new FileWriter(TMPFILE));
		writeEvent(out,
			args[0], 					// description
			Integer.parseInt(args[1]),	// mm start
			Integer.parseInt(args[2]),	// dd start
			Integer.parseInt(args[3]),	// mm end
			Integer.parseInt(args[4]));	// dd end
		out.close();
		// This is Mac OS X specific; on MS-Windows use "start".
		Runtime.getRuntime().exec(new String[]{"open", TMPFILE});
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {

		String event = request.getParameter("description");

		String yyStart = request.getParameter("yyStart");
		String mmStart = request.getParameter("mmStart");
		String ddStart = request.getParameter("ddStart");

		String yyEnd = request.getParameter("yyEnd");
		String mmEnd = request.getParameter("mmEnd");
		String ddEnd = request.getParameter("ddEnd");

		response.setContentType("ical");	// XXX FIXME

		PrintWriter out = response.getWriter();

		writeEvent(out, event,
			Integer.parseInt(yyStart),
			Integer.parseInt(mmStart),
			Integer.parseInt(ddStart),
			Integer.parseInt(yyEnd),
			Integer.parseInt(mmEnd),
			Integer.parseInt(ddEnd));
	}

	/** Write an event in the current year */
	public static void writeEvent(PrintWriter out, String event,
			int startMon, int startDay,
			int endMon, int endDay)
	{
		int thisyear = Calendar.getInstance().get(Calendar.YEAR);
		writeEvent(out, event,
			thisyear, startMon, startDay,
			thisyear, endMon, endDay);
	}

	/** Write an event.
	 * @param out - the PrintWriter to use.
	 * @param event - a textual description of the event.
	 * @param startYear - starting year (e.g., 2010)
	 * @param startMon  - starting month (
	 * @param startDay
	 * @param endYear - ending year (e.g., 2010)
	 * @param endMon
	 * @param endDay
	 */
	public static void writeEvent(PrintWriter out, String event,
			int startYear, int startMon, int startDay,
			int endYear, int endMon, int endDay)
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
		out.println("DTSTART;VALUE=DATE:" +
			int4digit(startYear) + int2digit(startMon) + int2digit(startDay));
		out.println("DTEND;VALUE=DATE:" +
			int4digit(endYear) + int2digit(endMon) + int2digit(endDay));
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
