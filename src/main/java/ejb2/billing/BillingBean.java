import javax.ejb.*;

public class BillingBean extends EntityBean {
	int amount;

	public void ejbCreate() {
		amount = 0;
	}

	public void setTotal(int amt) {
		amount = amt;
	}
}
