package ejb2.localinterfaces;

import javax.ejb.EntityBean;

// MemberBean.java - LocalHome interface
public abstract class MemberBean implements EntityBean {
	// LocalHome methods:
	// findByPrimaryKey implemented by Container

	// MemberLocal methods
	public abstract String getAddress(); // CMP method!
}
