package servlet;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.naming.*;
import javax.rmi.*;

public class EJBServlet extends HttpServlet {

	/** Compile-only Session EJB home interface */
	interface HelloHome{
		Hello create();
	}
	
	/** Compile-only Session EJB interface */
	interface Hello {
		String hello();
	}

	protected Hello hello;

	public void init() throws ServletException {

		try {
			Context initial = new InitialContext();

			Object objref = initial.lookup("Hello");

			HelloHome home =
				(HelloHome)PortableRemoteObject.narrow(objref, HelloHome.class);

			hello = home.create();

		} catch (Exception exc) {
			log(exc.toString());
			throw new ServletException(exc.getMessage());
		}
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
		  throws ServletException, IOException {

		PrintWriter out = response.getWriter();
		response.setContentType("text/html");

		out.println("<html><head><title>Hello</title></head>");
		out.println("<body>");
		out.println("<p>There is an EJB here and it says...");

		out.println("<b>" + hello.hello() + "</b>");
		out.println("</p>");
		out.println("</body></html>");
	}
}
