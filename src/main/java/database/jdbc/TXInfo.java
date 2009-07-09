package database.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * Print some information about transaction support on a given Driver.
 */
public class TXInfo {
	public static void main(String[] a) throws Exception {
	Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
	Connection con = DriverManager.getConnection(
		"jdbc:odbc:MusicVideo");
	int tx = con.getMetaData().getDefaultTransactionIsolation();
	String txtxt=null;
	switch(tx) {
		case Connection.TRANSACTION_NONE :
			txtxt = "TRANSACTION_NONE"; break;
		case Connection.TRANSACTION_READ_COMMITTED :
			txtxt = "TRANSACTION_READ_COMMITTED"; break;
		case Connection.TRANSACTION_READ_UNCOMMITTED :
			txtxt = "TRANSACTION_READ_UNCOMMITTED"; break;
		case Connection.TRANSACTION_REPEATABLE_READ :
			txtxt = "TRANSACTION_REPEATABLE_READ"; break;
        case Connection.TRANSACTION_SERIALIZABLE :
			txtxt = "TRANSACTION_SERIALIZABLE"; break;
		default:
			txtxt = "UNKNOWN!!";
	}
	System.out.println(txtxt);
	con.setTransactionIsolation(tx);
	System.out.println("Done");
	con.setTransactionIsolation(Connection.TRANSACTION_REPEATABLE_READ);
	System.out.println("TX is now " + con.getTransactionIsolation());
	}
}
