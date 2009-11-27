package i18n;

import java.util.ResourceBundle;

public enum EnumIntl {
	NORTH,
	EAST,
	SOUTH,
	WEST;
	static {
		ResourceBundle b = ResourceBundle.getBundle("i18n.EnumIntl");
		for (EnumIntl e : values()) {
			String description = b.getString(e.toString());
			e.description = description;
		}
	}
	private String description;
	public String getDescription() {
		return description;
	}
}
