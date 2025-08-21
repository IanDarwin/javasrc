package record;

/**
 * Demonstrate that records can implement have nested classes like a Builder
 */
public record RecordWithBuilder(String name) {

	// Try to make the canonical constructor private: NOT ALLOWED
	// private RecordWithBuilder(String name) {
	// 	this.name = name;
	// }

	public static class Builder {
		String name;

		Builder(){}

		Builder name(String name) {
			this.name = name;
			return this;
		}

		RecordWithBuilder build() {
			return new RecordWithBuilder(name);
		}
	}
		

	public static void main(String[] args) {

		RecordWithBuilder b = new RecordWithBuilder.Builder()
			.name("Help Me If You Can I'm Feeling Down")
			.build();

		System.out.println(b);
	}
}

