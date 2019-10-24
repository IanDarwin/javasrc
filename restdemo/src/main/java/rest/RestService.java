package rest;

import java.time.LocalDateTime;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * Simple REST Service
 */
// tag::main[]
@Path("")
@ApplicationScoped
public class RestService {
	
	public RestService() {
		System.out.println("RestService.init()");
	}

	@GET @Path("/timestamp")
	@Produces(MediaType.TEXT_PLAIN)
	public String getDate() {
		return LocalDateTime.now().toString();
	}

	/** A Hello message method
	 */
	@GET @Path("/greeting/{userName}")
	@Produces("text/html")
	public String doGreeting(@PathParam("userName")String userName) {
		System.out.println("RestService.greeting()");
		if (userName == null || userName.trim().length() <= 3) {
			return "Missing or too-short username";
		}
		return String.format("<h1>Welcome %s</h1><p>%s, We are glad to see you back!",
			userName, userName);
	}
	
	/** Used to download all items */
	@GET @Path("/names")
	@Produces(MediaType.APPLICATION_JSON)
	public List<String> findTasksForUser() {
		return List.of("Robin", "Jedunkat", "Lyn", "Glen");
	}
}
// end::main[]
