import javax.jdo.*;
import java.util.Properties;
import java.io.IOException;

/** TODO
 * A demonstration of serialization using JDO.
 * JDO is normally used to access a database, but can also be used
 * to save locally, which is shown here.
 */
public class SerialDemoJDO extends SerialDemoAbstractBase {

	public static void main(String[] args) throws IOException {
		SerialDemoJDO jd = new SerialDemoJDO();
		jd.save();
		jd.dump();
	}


	public PersistenceManager getPM() {
		Properties p = new Properties();
		p.setProperty("PersistenceManagerFactoryClass",
			"com.sun.jdori.common.PersistenceManagerFactoryImpl");
		PersistenceManagerFactory pmf = 
			JDOHelper.getPersistenceManagerFactory(p);
		return pmf.getPersistenceManager();
	}

	public void write(Object o) {
		PersistenceManager pm = getPM();
		pm.currentTransaction().begin();
		pm.makePersistent(o);
		pm.currentTransaction().commit();
		pm.close();
	}

	public void dump() {
		PersistenceManager pm = getPM();
		Object[] data = new Object[3];
		pm.retrieveAll(data);
		for (int i = 0; i < data.length; i++) {
			System.out.println(data[i]);
		}
		pm.close();
	}
}
