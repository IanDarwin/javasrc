package i18n;

import java.util.Locale;
import java.util.ResourceBundle;

public enum EnumIntl {
	NORTH,
	EAST,
	SOUTH,
	WEST;
	
	Locale savedLocale = null;
	
	final static String PROPS = "EnumIntl";
	
	void loadProps() {
		try {
			ResourceBundle b = ResourceBundle.getBundle(PROPS);
			for (EnumIntl e : values()) {
				String description = b.getString(e.toString());
				e.description = description;
			}
		} catch (Exception e) {
			System.err.println("Warning: could not load " + PROPS);
		}
	}
	
	private String description;
	public String getDescription() {
		if (Locale.getDefault() != savedLocale) {
			loadProps();
			savedLocale = Locale.getDefault();
		}
		if (description == null) {
			String name = toString();
			return name.charAt(0) + name.substring(1).toLowerCase();
		}
		return description;
	}
}
