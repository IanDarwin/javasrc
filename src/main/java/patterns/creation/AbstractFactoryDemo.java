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
		final ConnectionFactory connectionFactory = getConnectionFactory();
		final Object connection = connectionFactory.getConnection();
		System.out.println("ConnectionFactory " + connectionFactory + " gave us " + connection);
	}

	public static ConnectionFactory getConnectionFactory() {
		switch(type) {
			case JDBC: return new JdbcConnectionFactory();
			case JPA: return new JpaConnectionFactory();	// actually returns EntityManager
			case HIBERNATE: return new HibernateConnectionFactory(); // returns HibernateSession
			default:
				throw new IllegalArgumentException("Unknown Factory Type");
		}
	}

	// Dummy definitions to make this compile and sort of work
	interface ConnectionFactory {
		Object getConnection();
	}

	static class JdbcConnectionFactory implements ConnectionFactory {
		public Object getConnection() {
			return "My Dummy JDBC Connection";
		}
	}
	static class HibernateConnectionFactory implements ConnectionFactory {
		public Object getConnection() {
			return "My Fake Hibernate Session";
		}
	}
	static class JpaConnectionFactory implements ConnectionFactory {
		public Object getConnection() {
			return "Trust me! This is a JPA EntityManager";
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
