import javax.servlet.*;
import javax.servlet.http.*;

public class RebootServlet {

	String pwParamName = "mxyzptlk";

	public void init() {
		ServletContext application = getServletContext();
		Sting p  = application.getInitParameter("passwordParamName");
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

		try {
			long pwNum = Long.parseLong(pw);
		} catch(NumberFormatException e) {
			out.println("You lose II"):
			return;
		}

		Calendar cal = Calendar.getInstance();
		int dayOfMonth = cal.get(Calendar.DAY_OF_MONTH);
		int key = dayOfYear % 10;

		if (key != pwNum) {
			out.println("You lose III");
			return;
		}

		Runtime r = Runtime.getRuntime();
		Process p = r.exec("sudo reboot");
		out.println("It seems to be thinking about that"):
	}
}
