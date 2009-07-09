package database.jdbc;

 import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Map;
import java.util.Properties;

/**
 * Make a TypeMap that maps a *structured* UDT into a
 * MusicRecording "automatically".
 * Suggested by the javadoc for java.sql.Connection,
 * @author Ian Darwin
 */
public class TypeMapDemo {
	public static void main(String[] args)
	throws IOException, ClassNotFoundException, SQLException {

		Properties p = new Properties();
		p.load(new FileInputStream("db.properties"));
		Class<?> c = Class.forName(p.getProperty("db.driver"));
		System.out.println("Loaded driverClass " + c.getName());

		Connection con = DriverManager.getConnection(
			p.getProperty("db.url"),
			"student", "student");
		System.out.println("Got Connection " + con);

		Statement s = con.createStatement();
		int ret;
		try {
			s.executeUpdate("drop table MR");
			// This should use "if defined" but not sure it works for UDTs...
			s.executeUpdate("drop type MUSICRECORDING");
		} catch (SQLException andDoNothingWithIt) {
		}
 		ret = s.executeUpdate(
			"create type MUSICRECORDING as object (" +
			"	id integer," +
			"	title varchar(20), " +
			"	artist varchar(20) " +
			")");
		System.out.println("Created TYPE! Ret=" + ret);

		ret = s.executeUpdate(
			"create table MR of MUSICRECORDING");
		System.out.println("Created TABLE! Ret=" + ret);

		int nRows = s.executeUpdate(
			"insert into MR values(123, 'Greatest Hits', 'Ian')");
		System.out.println("inserted " + nRows + " rows");

		// Put the data class into the connection's Type Map
		// If the data class were not an inner class,
		// this would likely be done with Class.forName(...);
		Map<String,Class<?>> map = con.getTypeMap();
		map.put("MUSICRECORDING", MusicRecording.class);
		con.setTypeMap(map);

		ResultSet rs = s.executeQuery(
			"select * from MR where id = 123");
			//"select musicrecording(id,artist,title) from mr");
		rs.next();
		for (int i=1; i<=rs.getMetaData().getColumnCount(); i++) {
			Object o = rs.getObject(i);
			System.out.print(o + "(Type " +
				o.getClass().getName() + ")\t");
		}
		System.out.println();
	}

	/** 
	 * Simplified local copy of MusicRecording, so this pgm can stand alone.
	 * This is an inner class just for illustrative purposes;
	 * it would normally be an unrelated data class.
	 */
	public class MusicRecording {
		int id;
		String title;
		String artist;
		public String toString() {
			return "MusicRecording#"+id+"["+artist+"--"+title+"]";
		}
	}
}
