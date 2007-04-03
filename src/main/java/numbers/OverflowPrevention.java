package numbers;

public class OverflowPrevention {
	public static void main(String[] args) {
		int num = 20000;
		int size = 100000;
		int result = computeTotalSize(num, size);
		System.out.printf("Result = %d%n", result);
	}

	/** Multiply num * size, returning -1 if they would overflow */
	protected static int computeTotalSize(int num, int size) {
		if (num < 0 || size < 0) {
			throw new IllegalArgumentException();
		}
		if (Integer.MAX_VALUE / num < size) {
			System.err.printf("Result would exceed %d%n",
				Integer.MAX_VALUE);
			return -1;
		}  
		int result = num * size;
		return result;
	}
}
