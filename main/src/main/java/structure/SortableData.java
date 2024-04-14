package structure;

/**
 * A toy data class for demonstrating sorting with multiple fields
 */
class SortableData {
	int count;
	String name;
	public SortableData(int count, String name) {
		super();
		this.count = count;
		this.name = name;
	}
	@Override
	public String toString() {
		return "%5d %s".formatted(count, name);
	}
}