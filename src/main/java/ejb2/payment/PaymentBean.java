package ejb2.payment;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.ejb.EJBException;
import javax.ejb.SessionBean;
import javax.ejb.SessionContext;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class PaymentBean implements SessionBean, PaymentMethods {

	private static final long serialVersionUID = 3382194638497281743L;
	private Connection conn;
	private PreparedStatement ps;
	final static String PS_STRING = "insert into Payments " +
			"(pmtno, custId, ptype, amount) values (?,?,?,?)";

	/** pkey, a unique number for this payment transaction */
	int paymentID;

	public void ejbCreate() {
		setUpConnection();
	}

	public void ejbPostCreate(Integer i) {
	}

	public void ejbActivate(){
		setUpConnection();
	}

	public void ejbPassivate(){
		tearDownConnection();
	}
	 
	public void ejbRemove(){
	}
	 
	@SuppressWarnings("unused")
	private SessionContext ctx;

	public void setSessionContext(javax.ejb.SessionContext cx) {
		this.ctx = cx;
		setUpConnection();
	}
	 
	public void payByCash(int custno, double amt){
		process(Payment.CASH, custno, amt);
	}
	 
	public void payByCheque(int custno, double amt){
		process(Payment.CHEQUE, custno, amt);
	}
	 
	public void payByCredit(int custno, double amt){
		process(Payment.CREDIT, custno, amt);
	}

	protected void process(int type, int custNo, double amt) {
		try {
			ps.setInt(1, ++paymentID);
			ps.setInt(2, custNo);
			ps.setInt(3, type);
			ps.setDouble(4, amt);
			ps.executeUpdate();
		} catch (SQLException ex) {
			throw new EJBException(ex.toString());
		}
	}

	private void setUpConnection() {
		try {
			DataSource ds = (DataSource)new InitialContext().lookup("PaymentDS");
			conn = ds.getConnection();
			ps = conn.prepareStatement(PS_STRING);
		} catch (Exception e) {
			throw new EJBException(e.toString());
		}
	}

	private void tearDownConnection() {
		try {
			if (ps != null) {
				ps.close();
				ps = null;
			}
			if (conn != null) {
				conn.close();
				conn = null;
			}
		} catch (SQLException ex) {
			throw new EJBException(ex.toString());
		}
	}
}
