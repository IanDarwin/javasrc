package record;

/**
 * Java Record types are immutable, but what if you need to change a field?
 * Functional programming says: Just make a new instance!
 * This record has a convenience method to do just that,
 * using the xxxWith() pattern.
 */
public record RecordWith(int id, String name) {

	RecordWith withName(String newName) {
		return new RecordWith(id, newName);
	}

	public static void main(String[] args) {
		RecordWith r1 = new RecordWith(1, "Uganda");
		RecordWith r2 = r1.withName("Tonga");
		System.out.printf("r1 -> %s\n", r1);
		System.out.printf("r2 -> %s\n", r2);
	}
}

