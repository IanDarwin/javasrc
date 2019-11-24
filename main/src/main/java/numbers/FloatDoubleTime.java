package numbers;

public class FloatDoubleTime {
	/** How many times to do the loop */
	protected static final int HOW_MANY = 10000000;

	public static void main(String[] args) {
		long t0 = System.currentTimeMillis();
		float f = 0;
		for (int i=0; i<HOW_MANY; i++)
			f *= i;
		long t1 = System.currentTimeMillis();

		double d = 0;
		for (int i=0; i<HOW_MANY; i++)
			d *= i;
		long t2 = System.currentTimeMillis();

		System.out.println("Float:  " + (t1 - t0) + " " + f);

		System.out.println("Double: " + (t2 - t1) + " " + d);
	}
} 
