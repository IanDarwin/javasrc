package com.darwinsys.jndi;

import java.util.*;
import javax.naming.*;
import javax.naming.spi.*;

/** Trivial factory class to name in the Context.INITIAL_FACTORY property. */
public class ServicesContextFactory implements InitialContextFactory {

	/** Called from the InitialContext Constructor when the jndi.properties
	 * refers to ServicesContextFactory.
	 */
	public Context getInitialContext(Hashtable env) {
		return new Services(env);
	}
}
