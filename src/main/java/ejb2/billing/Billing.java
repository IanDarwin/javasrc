import javax.ejb.*

public interface Billing extends EJBObject {
	/** Generate a bill in the database */
	void setTotal(int amount);
}
