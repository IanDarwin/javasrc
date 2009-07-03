package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/** List all the values of all the Parameters */
public class ListParametersServlet extends HttpServlet {

	private static final long serialVersionUID = 9279187182L;
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
	throws IOException, ServletException {

		PrintWriter out = response.getWriter();

		out.println("<html><head><title>List Parameters</title></head>");
		out.println("<body><h1>Parameters</h1>");
		out.println("<ul>");
		Enumeration<String> e = request.getParameterNames();
		while (e.hasMoreElements()) {
			String key = e.nextElement();
			String[] values = request.getParameterValues(key);
			for (int i=0; i<values.length; i++) {
				out.println("<li>" + key + "[" + i + "] = " + values[i] + "</li>");
			}
		}
		out.println("</ul>");
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
	throws IOException, ServletException
	{
		doGet(request, response);
	}
}
