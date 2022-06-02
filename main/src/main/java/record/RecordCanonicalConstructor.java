package record;

public record RecordCanonicalConstructor(int rate, double amount) {

	/**
	 * The "Canonical Constructor" (which must be public) can be used
	 * to clamp values or do other value modifications/calculations
	 * on the parameters, and is run before the default constructor.
	 */
	public RecordCanonicalConstructor {
		if (rate < 0) {
			rate = 0;
		}
	}

	public static void main(String[] args) {
		System.out.println(new RecordCanonicalConstructor(-1, 42.0));
	}
}
