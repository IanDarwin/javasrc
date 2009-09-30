package restfulws;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * A raw-servlet implementation of the RESTful
 * Inventory Control application from Chapter 7
 * of Learning Tree Course 577, Java XML Web Services
 * <ul>
 * <li>HTTP GET to /all will return all items, in XML
 * <li>HTTP POST will update from posted XML which has
 *     product id and new quantity;
 * <li>HTTP PUT will insert new; URL is /3012, qty is in posted XML
 * <li>HTTP DELETE /3012 will delete record by id.
 * </ul>
 * @author Ian Darwin, "inspired" by code in a previous
 * version of the course note (re-created from memory).
 */
public class InventoryServletRaw extends HttpServlet {

	private static final long serialVersionUID = 5349596315091650623L;

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		final String method = request.getMethod();
		if ("GET".equals(method)) {
			response.setContentType("text/xml");
			PrintWriter out = response.getWriter();
			String command = getLastPath(request);
			if ("categories".equals(command)) {
				out.println("<categories>");
				for (String cat : getCategories()) {
					out.println("<category>" + cat + "</category>");
				}
				out.println("</categories>");
			} else if ("products".equals(command)) {
				// same deal but for all proeucts
			} else {
				throw new IllegalArgumentException(command);
			}
		} else if ("POST".equals(method)) {
			// do POST stuff
		} else if ("PUT".equals(method)) {
			// do PUT stuff
		} else if ("DELETE".equals(method)) {
			// do DELETE stuff
		} else throw new IllegalArgumentException("Method " + method + " not supported");
	}
	
	// Dummy methods to make the above appear to work
	private String[] getCategories() {
		return new String[]{ "Jazz", "Classical"};
	}
	
	private String getLastPath(HttpServletRequest req) {
		return req.getRequestURI().replaceAll(".*/", "");
	}
}
