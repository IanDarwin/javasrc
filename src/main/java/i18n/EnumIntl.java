package i18n;

import java.util.ResourceBundle;

public enum EnumIntl {
	NORTH,
	EAST,
	SOUTH,
	WEST;
	static {
		final String PROPS = "/i18n.EnumIntl";
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
		if (description == null) {
			String name = toString();
			return name.charAt(0) + name.substring(1).toLowerCase();
		}
		return description;
	}
}
