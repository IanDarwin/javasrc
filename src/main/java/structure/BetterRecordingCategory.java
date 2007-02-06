package structure50;

public enum BetterRecordingCategory {
	JAZZ("Jazz"),
	HIP_HOP("Hip Hop"),
	CLASSICAL("Classical");

	private String description;

	BetterRecordingCategory(String description) {
		this.description = description;
	}

	public String toString() {
		return description;
	}

	public static BetterRecordingCategory getCategory(String description) {
		for (BetterRecordingCategory cd : values())
			if (description.equals(cd.description))
				return cd;
		throw new IllegalArgumentException("No RecordingCategory for " + description);
	}
}
