package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serializable;
import java.lang.reflect.Field;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class HttpSessionInfoServlet extends HttpServlet {

	private static final long serialVersionUID = -1046433394491925556L;

	public void init(ServletConfig config) throws ServletException {
		super.init(config);
	}

	public String getServletInfo() {
		return "HttpSessionInfoServlet";
	}

	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		HttpSession sess = req.getSession(true);
		resp.setContentType("text/html");
		PrintWriter out = new PrintWriter(resp.getOutputStream());

		out.println("<HTML>");
		out
				.println("<HEAD><TITLE>HttpSessionInfoServlet Output</TITLE></HEAD>");
		out.println("<BODY BGCOLOR=\"white\">");

		out.println("<H1>HttpSessionInfoServlet Output</H1>");
		out.println("<P>Session object = " + sess.toString());
		if (sess instanceof Serializable) {
			out.println("<P>Hurrah! Session object IS serializable");
		} else {
			out.println("<P>Session object NOT serializable");
		}
		out.println("<P>Getting some fields...!");
		Field[] f = sess.getClass().getDeclaredFields();
		out.println("<OL>");
		for (int i = 0; i < f.length; i++)
			out.println("<LI>" + f[i]);
		out.println("<P>All done!");
		out.println("</BODY>");
		out.println("</HTML>");
		out.close();
	}

}
