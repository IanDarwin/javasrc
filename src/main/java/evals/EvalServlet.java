package evals;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class EvalServlet extends HttpServlet {
	public static String EVALS_FILE = "/home/ian/evals.txt";

	PrintWriter log;

	/** Initialize the servlet */
	public void init() {
		try {
			boolean append = true;
			log = new PrintWriter(new FileWriter(EVALS_FILE, append));
			log("Log opened, file = " + log);
		} catch (IOException e) {
			log("Failed to open writer");
		}
	}

	/** Service one request. */
	public void doGet(HttpServletRequest request,
		HttpServletResponse resp) throws IOException, ServletException {

		resp.setContentType("text/html");

		PrintWriter out = resp.getWriter();
		out.println("<HTML><HEAD><TITLE>Evaluation</TITLE>");
		out.println("<BODY BGCOLOR=\"yellow\">");

		out.println("<P>Version = 1");

		if (log == null) {
			out.println("<H3>Error</H3>");
			out.println("Sorry, unable to log (I/O error)");
			out.println("<P>Please notify your instructor at once!");
			return;
		}

		boolean errors = false;
		String name = request.getParameter("name");
		if (name == null || name.length() == 0) {
			out.println("<P>Please provide your name");
			errors = true;
		}
		int courseGrade = -1;
		String course = request.getParameter("course");
		if (course == null || course.length() == 0) {
			out.println("<P>Please provide a course grade");
			errors = true;
		}
		else courseGrade = Integer.parseInt(course);

		int instrGrade = 0;
		String instr= request.getParameter("instr");
		if (instr == null || instr.length() == 0) {
			out.println("<P>Please provide an instructor grade");
			errors = true;
		}
		else instrGrade = Integer.parseInt(instr);

		String pace = request.getParameter("pace");
		String best = request.getParameter("best");
		String improve = request.getParameter("improve");

		if (errors) {
			out.println("<P>Please use the \"Back\" button and try again.");
			return;
		}
		out.print("<H1>Thank you</H1>");
		String host = request.getRemoteHost();
		out.print("<P>Thanks for the comments");
		if (name.length() != 0) {
			out.print(", ");
			out.print(name);
		}
		out.print('@');
		out.println(host);

		out.print("<P>You rated the course a ");
		out.println(courseGrade);
		if (courseGrade <= 2) {
			out.print("<B>That is a low score!!</B>");
		}

		out.print("<P>You rated the instructor's performance a ");
		out.println(instrGrade);
		if (instrGrade <= 2) {
			out.print("<B>That is a low score!!</B>");
		}

		out.print("<P>You rated the pace as ");
		out.println(pace);

		if (best.length() != 0) {
			out.print("<P>You rated the best part as \"");
			out.print(best);
			out.println('\"');
		}
		if (improve.length() != 0) {
			out.print("<P>You suggested these improvements: \"" );
			out.println(improve);
			out.println('\"');
		}

		String reviewed;
		if (((reviewed = request.getParameter("reviewed")) != null) &&
			"true".equals(reviewed)) {
			loggit(name, host, courseGrade, instrGrade, pace,
				best, improve);
			out.println("<H3>Done!</H3><P>Your comments have been recorded.");
		} else {
			out.print("<BR>");
			// Kludge - seem to have to use POST here to
			// let the browser "see" the query string...
			out.print("<FORM METHOD=POST ACTION=");
			out.print(
				// Under JRun, host changed to "localhost"!!
				// HttpUtils.getRequestURL(request).
				new StringBuffer(
					"http://instructor:8080/servlet/EvalServlet").
					append("?reviewed=true&").
					append("?reviewed=true&").
					append(request.getQueryString()));
			out.println(">");
			out.print("<INPUT TYPE=submit VALUE=\"Press here to confirm\">");
			out.println("</FORM>");
		}
	}

	public void doPost(HttpServletRequest request,
		HttpServletResponse response)
			throws IOException, ServletException {
		doGet(request, response);
	}

	protected synchronized void loggit(String name, String host,
		int course, int instructor, String pace,
		String best, String improve) {
		log.print("N ");
		log.print(course);
		log.print('\t');
		log.print(instructor);
		log.print('\t');
		log.print(pace);
		log.print(" \"");
		log.print(name);
		log.print("\"@");
		log.print(host);
		log.println();
		if (best != null && best.length() != 0) {
			log.print("BEST ");
			log.print(best.replace('\r', ' ').replace('\n', ' '));
			log.println();
		}
		if (improve != null && improve.length() != 0) {
			log.print("IMPROVE ");
			log.print(improve.replace('\r', ' ').replace('\n', ' '));
			log.println();
		}
		log.flush();
	}
}
