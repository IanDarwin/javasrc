package ejb2.billing;

import javax.ejb.EJBObject;

public interface Billing extends EJBObject {
	/** Generate a bill in the database */
	void setTotal(int amount);
}
