package jaxrsservice;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * A start at a JAX-RS implementation of the RESTful
 * Inventory Control application from Chapter 7
 * of Learning Tree Course 577, Java XML Web Services
 * <ul>
 * <li>HTTP GET to /all will return all items, in JSON (or XML)
 * <li>HTTP POST will update from posted XML which has
 *     product id and new quantity;
 * <li>HTTP PUT will insert new; URL is /3012, qty is in posted XML
 * <li>HTTP DELETE /3012 will delete record by id.
 * </ul>
 * @author Ian Darwin, "inspired" by code in the course notes (re-created from memory).
 */
public class InventoryServiceJAXRS extends Object {

	private static final long serialVersionUID = 5349596315091650623L;

	@GET @Path("/all")
	@Produces({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
	protected String getCategories() {
		String x = "<categories>";
		for (String cat : fakeDaoGetCategories()) {
			x += "<category>" + cat + "</category>";
		}
		x += "</categories>";
		return x;
	}
	
	@POST
	protected void update() {
		// do the work here
	}
	
	@DELETE
	protected String deleteRecord() {
		return "<OK>Record Deleted</OK>";
	}
	
	// Dummy methods to make the above appear to work
	private String[] fakeDaoGetCategories() {
		return new String[]{ 
			"Classical",
			"Country",
			"Jazz", 
			"Rock", 
		};
	}
}
