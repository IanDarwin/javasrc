package xml.jaxb;

import javax.xml.bind.annotation.XmlRegistry;

/**
 * Object Factory for config.xsd.
 */
@XmlRegistry
public class ObjectFactory {

	/**
	 * Create the ObjectFactory
	 */
	public ObjectFactory() {
	}

	/**
	 * Create the instance of {@link Configuration }
	 */
	public Configuration createConfiguration() {
		return new Configuration();
	}
}
