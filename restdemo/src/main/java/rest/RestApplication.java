package rest;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

@ApplicationPath("")
public class RestApplication extends Application {

	public RestApplication() {
		System.out.println("RestApplication.RestApplication()");
	}

	/** 
	 * Optional efficiency tweak - only scan for annotations in
	 * classes in this Set.
	 */
	@Override
	public Set<Class<?>> getClasses() {
		System.out.println("RestApplication.getClasses()");
		Set<Class<?>> cl = new HashSet<>();
		cl.add(RestService.class);
		return cl;
	}
}
