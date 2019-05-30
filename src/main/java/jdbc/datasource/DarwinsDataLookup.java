package database.jdbc.datasource;

import java.sql.Connection;

import javax.naming.InitialContext;
import javax.sql.DataSource;

public class DarwinsDataLookup {
	public static void main(String[] argv)  throws Exception {

		// Find DataSource from JNDI
		System.getProperties().setProperty("java.naming.factory.initial",
			"com.sun.jndi.rmi.registry.RegistryContextFactory");
		System.getProperties().setProperty("java.naming.provider.url",
			"rmi://localhost/");
		DataSource ds =
			(DataSource)new InitialContext().lookup("darwinsys/RainData");

		// Test it out...
		Connection conn = ds.getConnection("student", "student");
		System.out.println("Connection = " + conn);
		conn.close();
	}
}