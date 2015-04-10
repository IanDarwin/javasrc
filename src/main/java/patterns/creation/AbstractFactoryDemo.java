package patterns.creation;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * A really really simple example of a configurable AbstractFactory
 * Reads from a Properties file on classpath of the form
 * dao_category= one of the FactoryType
 * e.g.:
 * renderer=patterns.creation.MyRenderer
 */
public class AbstractFactoryDemo {

	private static final String FACTORY_CONFIG_RESOURCE_NAME = 
				"/patterns/creation/factory.config";

	enum FactoryType { 
		JDBC,
		JPA,
		HIBERNATE
	}
	static FactoryType type;

	public static void main(String[] args) throws Exception {
		type = FactoryType.valueOf(props.getProperty("dao_type"));
		final DaoFactory daoFactory = getDaoFactory();
		final Object musicDao = daoFactory.getMusicDao();
		System.out.println("Factory " + daoFactory + " gave us " + musicDao);
	}

	public static DaoFactory getDaoFactory() {
		switch(type) {
			case JDBC: return new JdbcConnectionFactory();
			case JPA: return new JpaConnectionFactory();	// actually returns EntityManager
			case HIBERNATE: return new HibernateConnectionFactory(); // returns HibernateSession
			default:
				throw new IllegalArgumentException("Unknown Factory Type");
		}
	}

	// Dummy definitions to make this compile and sort of work
	interface DaoFactory {
		Object getMusicDao();
		// Object getVideoDao();
		// Object getBookDao();
	}

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
