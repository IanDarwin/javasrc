package jdbc;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class MigrationTest {
	
	String[][] names = {
			{ "Jeane", "D'Arc" },
			{ "John", "Henry" },
			{ "Robin", "Smith" }
	};
	private String CREATE_STATEMENT = "create table student (firstname varchar(42), lastname varchar(42))";
	Connection inConnection, outConnection;

	@BeforeEach
	void setUp() throws Exception {
		inConnection = DriverManager.getConnection("jdbc:hsqldb:mem:originalDb", "SA", "");
		inConnection.createStatement().executeUpdate(CREATE_STATEMENT);
		PreparedStatement pst = inConnection.prepareStatement("Insert into student (firstname, lastname) values(?,?);");
		for (String[] student : names) {
			System.out.println("MigrationTest.setUp(): insert " + student[1]);
			pst.setString(1, student[0]);
			pst.setString(2, student[1]);
			pst.executeUpdate();
		}
		
		outConnection = DriverManager.getConnection("jdbc:hsqldb:mem:copiedDb", "SA", "");
		outConnection.createStatement().executeUpdate(CREATE_STATEMENT);
	}

	@Test
	void test() throws Exception {
		
		// Try to populate the database in "outConnection"
		MigrateData.migrate(inConnection, outConnection);
		
		PreparedStatement pst = 
		     outConnection.prepareStatement(MigrateData.SELECT_STUDENTS_STATEMENT);
		ResultSet rs =pst.executeQuery();
		int n = 0;
		while (rs.next()) {
			System.out.println("MigrationTest.test()");
			assertEquals(names[n][0], rs.getString(1));
			assertEquals(names[n][1], rs.getString(2));
			++n;
		}
		assertEquals(names.length, n, "number of rows copied");
	}
}
