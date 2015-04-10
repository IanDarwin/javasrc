package patterns.creation;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * A really really simple example of a configurable AbstractFactory
 * This Abstract Factory gives out DAO's whose type is
 * determined from the properties file, and which would
 * (in a fuller example) in turn give out, say,
 * JPAMusicDao, JPAVideoDao, JPABookDao, etc., when the
 * configuration was set to JPA.
 * <br/>
 * Reads from a Properties file on classpath of the form
 * dao_category= one of the FactoryType
 * e.g.:
 * dao_category=JPA
 */
public class AbstractFactoryDemo {

	// This "file name" is used to load the props from classpath
	// Since it's in this same directory, the path is the
	// full package name follwed by the filename.
	private static final String FACTORY_CONFIG_RESOURCE_NAME = 
				"/patterns/creation/factory.config";

	/** Enumerate all the supported DAO technologies */
	enum FactoryTechnology { 
		JDBC,
		JPA,
		HIBERNATE
	}
	static FactoryTechnology tech;

	public static void main(String[] args) throws Exception {
		// Get the string name of the FactoryTechnology to use
		final String property = props.getProperty("dao_type");
		// Get the enum that goes with it
		tech = FactoryTechnology.valueOf(property);
		// Get the DaoFactory of the configured type
		final DaoFactory daoFactory = getDaoFactory();
		// Pick one (the only one implemented so far) DAO
		final Object musicDao = daoFactory.getMusicDao();
		// Print it out for verification.
		System.out.println("Factory " + daoFactory + " gave us " + musicDao);
	}

	/**
	 * The Abstract Factory Method: gives the concrete factory.
	 * @return The configured DaoFactory.
	 */
	public static DaoFactory getDaoFactory() {
		switch(tech) {
			case JDBC: return new JdbcConnectionFactory();
			case JPA: return new JpaConnectionFactory();	// actually returns EntityManager
			case HIBERNATE: return new HibernateConnectionFactory(); // returns HibernateSession
			default:
				throw new IllegalArgumentException("Unknown Factory Type");
		}
	}

	/** Dummy definition of the DaoFactory, just enough
	 *  to make this compile and sort of work.
	 *  The return value is Object, in reality it would
	 *  be something like MusicDao, but I wanted to keep
	 *  this example short.
	 */
	interface DaoFactory {
		Object getMusicDao();
		// Object getVideoDao();
		// Object getBookDao();
	}

	// The various concrete Factory classes.
	static class JdbcConnectionFactory implements DaoFactory {
		public Object getMusicDao() {
			return "My Dummy JDBC Music Dao";
		}
	}
	static class HibernateConnectionFactory implements DaoFactory {
		public Object getMusicDao() {
			return "My Fake Hibernate Music Dao";
		}
	}
	static class JpaConnectionFactory implements DaoFactory {
		public Object getMusicDao() {
			return "Trust me! This is a JPA Music Dao";
		}
	}
	
	// Stuff for loading properties
	static Properties props = new Properties();

	// Static initializer to load the properties file.
	static {
		try {
			InputStream stream = 
				AbstractFactoryDemo.class.getResourceAsStream(FACTORY_CONFIG_RESOURCE_NAME);
			if (stream == null) {
				throw new ExceptionInInitializerError("Can't load properties file from classpath: " + FACTORY_CONFIG_RESOURCE_NAME);
			}
			props.load(stream);
		} catch (IOException e) {
			throw new ExceptionInInitializerError(e);
		}
	}
}
