package xml.jaxb;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * Demo of XML via JAXB; meant to represent some of the (many!)
 * fields in a typical GUI for user<-->application configuration.
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "configuration", propOrder={"screenName", "webProxy", "verbose", "colorName"})
@XmlRootElement(name = "config")
public class Configuration
{
	private String webProxy;
	private boolean verbose;
	private String colorName;
	private String screenName;
	
	public String getColorName() {
		return colorName;
	}
	public void setColorName(String colorName) {
		this.colorName = colorName;
	}

	public boolean isVerbose() {
		return verbose;
	}
	public void setVerbose(boolean verbose) {
		this.verbose = verbose;
	}
	
	public String getWebProxy() {
		return webProxy;
	}
	public void setWebProxy(String webProxy) {
		this.webProxy = webProxy;
	}
	
	public String getScreenName() {
		return screenName;
	}
	public void setScreenName(String screenName) {
		this.screenName = screenName;
	}
}
