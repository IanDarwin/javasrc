import java.util.*;
import javax.ejb.*;
import javax.rmi.*;
import javax.naming.*;
import javax.transaction.*;

/** GetUserTX - demo of using UserTransaction (JTA) from client.
 */
public class GetUserTX {
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
			System.out.println("New TX, status = " + tx.getStatus());

			tx.begin();
			System.out.println("Begun, status = " + tx.getStatus());
			try {
				method1();
				method2();
				tx.commit();
				System.out.println("Committed, status = " + tx.getStatus());
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
	}

	static void method2() {
	}
}
