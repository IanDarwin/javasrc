package json;

import java.util.List;

public class SoftwareInfo {
	public String name;
	public String version;
	public String description;
	public String className;
	public List<String> contributors;
	
	@Override
	public String toString() {
		return String.format("Software: %s (%s) by %s", name, version, contributors);
	}
}
