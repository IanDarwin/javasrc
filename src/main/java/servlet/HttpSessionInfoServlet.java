package servlet;

// package darwinsys;

import java.io.*;
import java.lang.reflect.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class HttpSessionInfoServlet extends HttpServlet
{
	public void init(ServletConfig config) throws ServletException
	{
		super.init(config);

		

	}

	public String getServletInfo()
	{
		return "HttpSessionInfoServlet";
	}

	public void doGet(HttpServletRequest req, HttpServletResponse resp)
	throws ServletException, IOException
	{
	    HttpSession sess = req.getSession(true);
		resp.setContentType("text/html");
		PrintWriter out = new PrintWriter(resp.getOutputStream());

		out.println("<HTML>");
		out.println("<HEAD><TITLE>HttpSessionInfoServlet Output</TITLE></HEAD>");
		out.println("<BODY BGCOLOR=\"white\">");

		out.println("<H1>HttpSessionInfoServlet Output</H1>");
		out.println("<P>Session object = " + sess.toString());
		if (sess instanceof Serializable) {
		    out.println("<P>Hurrah! Session object IS serializable");
		} else {
		    out.println("<P>Session object NOT serializable");
		}
		out.println("<P>Getting some fields...!");
		Class c = sess.getClass();
		Field[] f = c.getDeclaredFields();
		out.println("<OL>");
		for (int i=0; i<f.length; i++)
		    out.println("<LI>" + f[i]);
		out.println("<P>All done!");
		out.println("</BODY>");
		out.println("</HTML>");
		out.close();
	}
	
	

}
