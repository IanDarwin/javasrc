package io;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Collection;
import java.util.Properties;

import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;

/**
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
		try {
			p.load(new FileInputStream("jdo.properties"));
			PersistenceManagerFactory pmf = 
				JDOHelper.getPersistenceManagerFactory(p);
			return pmf.getPersistenceManager();
		} catch (IOException ex) {
			throw new RuntimeException(ex.toString());
		}
	}

	public void write(Object o) {
		PersistenceManager pm = getPM();
		pm.currentTransaction().begin();
		if (o instanceof Collection) {
			pm.makePersistentAll((Collection)o);
		} else {
			pm.makePersistent(o);
		}
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
