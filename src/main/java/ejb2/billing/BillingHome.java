import javax.ejb.*

public interface BillingHome extends EJBHome {
	public Billing create(int CustID);
}
