package restfulws;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * A start at a JAX-RS implementation of the RESTful
 * Inventory Control application from Chapter 7
 * of Learning Tree Course 577, Java XML Web Services
 * <ul>
 * <li>HTTP GET to /all will return all items, in XML
 * <li>HTTP POST will update from posted XML which has
 *     product id and new quantity;
 * <li>HTTP PUT will insert new; URL is /3012, qty is in posted XML
 * <li>HTTP DELETE /3012 will delete record by id.
 * </ul>
 * @author Ian Darwin, "inspired" by code in the course notes (re-created from memory).
 */
public class InventoryServletJAXRS extends HttpServlet {

	private static final long serialVersionUID = 5349596315091650623L;

	protected String getCategories() {
		String x = "<categories>";
		for (String cat : DAOgetCategories()) {
			x += "<category>" + cat + "</category>";
		}
		x += "</categories>";
		return x;
	}
	
	protected String deleteRecord() {
		return "<OK>Record Deleted</OK>";
	}
	
	// Dummy methods to make the above appear to work
	private String[] DAOgetCategories() {
		return new String[]{ "Jazz", "Classical"};
	}
}
