/**
 * Simple Hello World demo program.
 */
public class MyArray {
	public static void main(String argv[]) {
		// Want a 10-by-24 array
		int me[][] = new int[10][];
		for (int i=0; i<10; i++)
			me[i] = new int[24];

		System.out.println("Hello, World of Java");
		System.out.println(me.length);
		System.out.println(me[0].length);
	}
}
