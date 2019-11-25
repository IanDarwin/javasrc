package ejb2.serverinfo;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.rmi.PortableRemoteObject;
import javax.transaction.Status;
import javax.transaction.UserTransaction;

/** GetUserTX - demo of using UserTransaction (JTA) from client.
 */
public class UserTX {

	public static void main(String[] args) {

		UserTransaction tx;
		try {
			// Properties from jndi.properties in current directory
			Context ctx = new InitialContext();

			Object objref = ctx.lookup("UserTransaction");
			tx =
				(UserTransaction)PortableRemoteObject.narrow(objref,
					UserTransaction.class);

			System.out.println("Transaction is: " + tx);
			System.out.println("New TX, status = " + printStatus(tx));

			tx.begin();
			System.out.println("Begun, status = " + printStatus(tx));
			try {
				method1();
				method2();
				tx.commit();
				int st = tx.getStatus();
				if (st == Status.STATUS_COMMITTED) {
					System.out.println("Committed, status = OK");
				} else {
					System.out.println("Committed, but status = " +
						printStatus(tx));
				}
			} catch (Exception ex) {
				tx.rollback();
				System.out.println("Rolled back, status = " + tx.getStatus());
			}


		} catch (Exception ex) {
			System.err.println("Caught an unexpected exception!");
			ex.printStackTrace();
		}
	}

	static void method1() {
		// Do something to the database...
	}

	static void method2() {
		// Do something to the database...
	}

	public static String printStatus(UserTransaction tx) throws Exception {
		switch(tx.getStatus()) {
		case Status.STATUS_ACTIVE: return "ACTIVE";
		case Status.STATUS_COMMITTED: return "COMMITTED";
		case Status.STATUS_COMMITTING: return "COMMITTING";
		case Status.STATUS_MARKED_ROLLBACK: return "MARKED_ROLLBACK";
		case Status.STATUS_NO_TRANSACTION: return "NO_TRANSACTION";
		case Status.STATUS_PREPARED: return "PREPARED";
		case Status.STATUS_PREPARING: return "PREPARING";
		case Status.STATUS_ROLLEDBACK: return "ROLLEDBACK";
		case Status.STATUS_ROLLING_BACK: return "ROLLING_BACK";
		case Status.STATUS_UNKNOWN: return "UNKNOWN";
		default: return "UNKNOWN STATUS VALUE: " + tx.getStatus();
		}
	}
}
