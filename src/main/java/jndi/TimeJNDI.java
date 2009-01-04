package jndi;

import java.io.InputStream;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.rmi.Remote;
import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

/** Measure the time taken for the Initial Context constructor
 * @version $Id$
 */
public class TimeJNDI {

		static long startTime, endTime;

        public static void main(String[] av) throws Exception {

			// Name of properties file to load
			String name = av.length == 1 ? av[0] : "jndi";

			start(); end(); report("Calibration run");

			Properties p = new Properties();
			String propsName = name + ".properties";
			InputStream inputStream = 
				TimeJNDI.class.getClassLoader().getResourceAsStream(propsName);
			if (inputStream == null) {
				throw new RuntimeException("Can't load " + propsName);
			}
			p.load(inputStream);

			start();
			Context ctx = new InitialContext(p);
			end();
			report("InitialContext setup");

			MyObject o = new MyObject();
			start();
			ctx.rebind("randomstuff", o);
			end();
			report("Rebind");

			start();
			Object q = ctx.lookup("randomstuff");
			end();
			report("Lookup of " + q);
		}
		static class MyObject implements Remote, Serializable {
			final static long serialVersionUID = 0x12345678;
			// empty
		}
		static void start() {
			startTime = System.currentTimeMillis();
		}
		static void end() {
			endTime = System.currentTimeMillis();
		}
		static void report(String mesg) {
			System.out.printf("%s took %6.3f seconds%n",
				mesg, (endTime - startTime) / 1000D);
		}
}

