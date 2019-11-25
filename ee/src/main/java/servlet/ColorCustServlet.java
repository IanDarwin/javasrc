package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/** Color customization servlet */
public class ColorCustServlet extends HttpServlet {

	protected final static String DEFAULT_COLOR = "white";
	protected String faveColor = DEFAULT_COLOR;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
	throws IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();

		String cand=request.getParameter("color_name");
		if (cand != null) {
			faveColor = cand;
			Cookie c = new Cookie(CookieServlet.PREFS_BGCOLOR, faveColor);
			c.setMaxAge(60*60*24*365);
			response.addCookie(c);
		}
		response.sendRedirect("/servlet/CookieServlet");
	}
}
