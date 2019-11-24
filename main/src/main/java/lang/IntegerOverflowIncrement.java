package lang;

public class IntegerOverflowIncrement {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int i = Integer.MAX_VALUE;
		System.out.println(i);
		++i;
		System.out.println(i);
		++i;
		System.out.println(i);
	}

}
