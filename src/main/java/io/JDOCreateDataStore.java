import java.io.*;
import java.util.*;
import javax.jdo.*;

/** Create a DataStore. Adapted from the JavaRanch JDO tutorial */
public class JDOCreateDataStore {
	public static void main(String[] args) throws IOException {
			Properties p = new Properties();
			p.load(new FileInputStream("jdo.properties"));
			p.setProperty("com.sun.jdori.option.ConnectionCreate", "true");            
			PersistenceManagerFactory pmf =
					JDOHelper.getPersistenceManagerFactory(p);
			PersistenceManager pm = pmf.getPersistenceManager();
			Transaction tx = pm.currentTransaction();
			tx.begin();
			tx.commit();
	}
}
