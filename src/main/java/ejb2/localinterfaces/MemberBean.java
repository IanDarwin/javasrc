// MemberBean.java - LocalHome interface
import javax.ejb.*;
public abstract class MemberBean implements EntityBean {
	// LocalHome methods:
	// findByPrimaryKey implemented by Container

	// MemberLocal methods
	public abstract String getAddress(); // CMP method!
}
