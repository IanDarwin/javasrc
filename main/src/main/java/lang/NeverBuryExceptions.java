package lang;

public class NeverBuryExceptions {
	void exceptionTranslationIsGood() {
		try {
			int x = 42 / 0;
			System.out.println(x);
		} catch (RuntimeException e) {
			throw new IllegalArgumentException("Numeric error", e);
		}
	}
	
	void buryExceptionIsBad() {
		try {
			int y = 57 / 0;
			System.out.println(y);
		// NEVER DO THS: EVER!
		} catch (Exception e) {}
	}
}
