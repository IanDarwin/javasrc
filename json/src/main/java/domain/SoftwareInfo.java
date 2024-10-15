package domain;

import java.util.List;

// tag::main[]
public record SoftwareInfo(String name,
	String version, String description,
	String className, List<String> contributors) {
	
	@Override
	public String toString() {
		return String.format("Software: %s (%s) by %s", name, version, contributors);
	}
}
// end::main[]
