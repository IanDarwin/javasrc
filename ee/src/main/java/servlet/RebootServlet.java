package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Calendar;

import javax.servlet.*;
import javax.servlet.http.*;

/** Reboot servlet. The program accepts one parameter from
 * the HTTP request, which must be a single numeric string == DAY_OF_YEAR % 10.
 * The NAME of this parameger is given by pwParamaName, which may be set
 * in the context as "passwordParamName", and if not set, defaults to
 * DEFAULT_PARAM_NAME.
 */
public class RebootServlet extends HttpServlet {

	private static final long serialVersionUID = 3257291322644705584L;
	private final static String DEFAULT_PARAM_NAME = "mxyzptlk";

	String pwParamName = DEFAULT_PARAM_NAME;

	public void init() {
		ServletContext application = getServletContext();
		String p  = application.getInitParameter("passwordParamName");
		if (p != null) 
			pwParamName = p;
	}

	public void doGet(HttpServletRequest request,
		HttpServletResponse response) throws IOException {

		PrintWriter out = response.getWriter();
		out.println("<h2>Since you asked...</h2>");

		String pw = request.getParameter(pwParamName);
		if (pw == null) {
			out.println("You lose");
			return;
		}

		long pwNum = 0;
		try {
			pwNum = Long.parseLong(pw);
		} catch(NumberFormatException e) {
			out.println("You lose II");
			return;
		}

		Calendar cal = Calendar.getInstance();
		int dayOfYear = cal.get(Calendar.DAY_OF_YEAR);
		int key = dayOfYear % 10;

		if (key != pwNum) {
			out.println("You lose III");
			return;
		}

		Runtime r = Runtime.getRuntime();
		r.exec(new String[]{"sudo", "reboot"});
		out.println("It seems to be thinking about that");
		out.flush();
	}
}
