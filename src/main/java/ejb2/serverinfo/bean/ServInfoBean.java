package com.darwinsys.ejb;

import javax.ejb.*;
import javax.naming.*;

/**
 *  Implementation Class for ServInfo.
 *
 * @author Ian Darwin, with help from MkEjb
 */
public class ServInfoBean implements SessionBean {

	// fields -- none

	// implementation of main logic

	public Object getEnv(String eName) { 
		try {
			Context ctx = new InitialContext();
			Object o = ctx.lookup("java:comp/env/" + eName);
			return o;
		} catch (Exception ex) {
			return ex.toString();
		}
	}

	// Create Methods:

	// method implementation for Create/PostCreate
	public void ejbCreate(){
		return;
	}
	public void ejbPostCreate(){
		return;
	}

	// No Find methods  for session bean

	/** reference to passed Session context */
	private SessionContext ctx;

	// method implementations imposed by SessionBean interface

	/** save the session context */
	public void setSessionContext(SessionContext x) { ctx = x; }
	public void unsetSessionContext() { ctx = null; }

	public void ejbLoad() {}	// entity
	public void ejbStore() {}	// entity
	public void ejbActivate() {}
	public void ejbPassivate() {}
	public void ejbRemove() {}
}
