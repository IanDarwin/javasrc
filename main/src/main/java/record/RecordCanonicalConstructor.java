package record;

/**
 * The "Compact Constructor" has no parameter list.
 * The "Canonical Constructor" (which must be public) can be used
 * to clamp values or do other value modifications/calculations
 * on the parameters, and is run before the default constructor.
 * Note the syntax: public CLAZZNAME { ... } (no parens!).
 */
public record RecordCanonicalConstructor(int rate, double amount) {

	/** In this case the Compact Constructor - the one with no params -
	 * inherits the parameter list and becomes the Canonical Constructor.
	 */
	public RecordCanonicalConstructor {
		if (rate < 0) {
			rate = 0;
		}
	}

	RecordCanonicalConstructor() {
		this(0, 0);
	}

	public static void main(String[] args) {
		System.out.println(new RecordCanonicalConstructor(-1, 42.0));
		System.out.println(new RecordCanonicalConstructor());
	}
}
