package servlet;

import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;

/** List all the values of all the Parameters */
public class ListParametersServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
	throws IOException, ServletException
	{

		PrintWriter out = response.getWriter();

		out.println("<html><head><title>List Parameters</title></head>");
		out.println("<body><h1>Parameters</h1>");
		out.println("<ul>");
		Enumeration e = request.getParameterNames();
		while (e.hasMoreElements()) {
			String key = (String)e.nextElement();
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
