package functional;

@FunctionalInterface
// BEGIN define
interface MyFunctionalInterface {
    int compute(int x);
}
// END define

public class ProcessIntsUsingFunctional {
	// BEGIN use
	static int[] integers = {1, 2, 3};

	public static void main(String[] args) {
		int total = 0;
		for (int i : integers) 
			total += process(i, x ->  x * x + 1);
		System.out.println("The total is " + total);
	}

	private static int process(int i, MyFunctionalInterface o) {
		return o.compute(i);
	}
	// END use
}

