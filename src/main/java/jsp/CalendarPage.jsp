<%@page import="java.util.*,java.text.*" %>

<head>
	<title>Print a month page.</title>
	<meta name="version"
		content="$Id$">
</head>
<h1>Print a month page, for the Western calendar.</h1>
<P>Author Ian F. Darwin, ian@darwinsys.com

<%	// First get the month and year from the form.
	boolean yyok = false;	// -1 is a valid year, use boolean
	int yy = 0, mm = 0;
	String yyString = request.getParameter("year");
	if (yyString != null && yyString.length() > 0) {
		try {
			yy = Integer.parseInt(yyString);
			yyok = true;
		} catch (NumberFormatException e) {
			out.println("Year " + yyString + " invalid");
		}
	}
	if (!yyok)
		yy = 2000;	// XX from new Calendar().

	String mmString = request.getParameter("month");
	if (mmString != null) {
		for (int i=0; i<months.length; i++)
			if (months[i].equals(mmString)) {
				mm = i;
				break;
			}
	}
 %>

<form method=post action="/tmp/CalendarPage.jsp">
	Month: <select name=month>
	<% for (int i=0; i<months.length; i++) {
		if (i==mm)
			out.print("<option selected>");
		else
			out.print("<option>");
		out.print(months[i]);
		out.println("</option>");
	}
	%>
	</select>
	Year (4-digit): 
		<input type="text" size="5" name="year"
			value="<%= yy %>"></input>
	<input type=submit value="Display">
</form>
<%!
	/** The names of the months */
	String[] months = {
		"January", "February", "March", "April",
		"May", "June", "July", "August",
		"September", "October", "November", "December"
	};

	/** The days in each month. */
	int dom[] = {
			31, 28, 31, 30,	/* jan feb mar apr */
			31, 30, 31, 31, /* may jun jul aug */
			30, 31, 30, 31	/* sep oct nov dec */
	};
%>

<%
	/** The number of days to leave blank at the start of this month */
	int leadGap = 0;
%>
<table border=1>
<tr><th colspan=7><%= months[mm] %>  <%= yy %></tr>

<%		Calendar calendar = new GregorianCalendar(yy, mm, 1); %>
<tr><td>Su<td>Mo<td>Tu<td>We<td>Th<td>Fr<td>Sa</tr>

<%
		// Compute how much to leave before the first.
		// getDay() returns 0 for Sunday, which is just right.
		leadGap = calendar.get(Calendar.DAY_OF_WEEK)-1;

		int daysInMonth = dom[mm];
		if (isLeap(calendar.get(Calendar.YEAR)) && mm == 1)
			++daysInMonth;

		out.print("<tr>");

		// Blank out the labels before 1st day of month
		for (int i = 0; i < leadGap; i++) {
			out.print("<td>&nbsp;");
		}

		// Fill in numbers for the day of month.
		for (int i = 1; i <= daysInMonth; i++) {

			out.print("<td>");
			out.print(i);
			out.print("</td>");

			if ((leadGap + i) % 7 == 0) {		// wrap if end of line.
				out.println("</tr>");
				out.print("<tr>");
			}
		}
%>
</tr>
</table>
<%!
	/**
	 * isLeap() returns true if the given year is a Leap Year.
	 *
	 * "a year is a leap year if it is divisible by 4
	 * but not by 100, except that years divisible by 400
	 * *are* leap years." 
	 *	-- Kernighan & Ritchie, _The C Programming Language_, p 37.
	 */
	public boolean isLeap(int year) {
		if (year % 4 == 0 && year % 100 != 0 || year % 400 == 0)
			return true;
		return false;
	}
%>
