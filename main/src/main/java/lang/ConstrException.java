package lang;

// Just to show that constructors are allowed to throw exceptions

public class ConstrException {
	public ConstrException(int i) {
		if (i < 0)
			throw new IllegalArgumentException("value " + i +
				"invalid, must be non-negative");
	}
}
