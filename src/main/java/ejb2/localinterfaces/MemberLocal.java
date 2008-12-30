package ejb2.localinterfaces;

import javax.ejb.EJBLocalObject;

/** MemberLocal.java - Local business interface */
public interface MemberLocal extends EJBLocalObject {
	public String getAddress();
}
