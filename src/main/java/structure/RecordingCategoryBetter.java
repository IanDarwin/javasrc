package structure;

public enum RecordingCategoryBetter {
	JAZZ("Jazz"),
	HIP_HOP("Hip Hop"),
	CLASSICAL("Classical");

	private String description;

	RecordingCategoryBetter(String description) {
		this.description = description;
	}

	public String toString() {
		return description;
	}

	public static RecordingCategoryBetter getCategory(String description) {
		for (RecordingCategoryBetter cd : values())
			if (description.equals(cd.description))
				return cd;
		throw new IllegalArgumentException("No RecordingCategory for " + description);
	}
}
