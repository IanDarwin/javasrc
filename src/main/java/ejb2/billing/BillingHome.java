package ejb.billing;

import javax.ejb.EJBHome;

public interface BillingHome extends EJBHome {
	public Billing create(int CustID);
}
