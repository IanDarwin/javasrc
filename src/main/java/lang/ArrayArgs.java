package lang;

public class ArrayArgs {
	ArrayArgs(int[] foo) {
		super();
	}
	public static void main(String[] argv) {
		// new ArrayArgs( { 1,2,3} );
		int myDate[] = {1,2,3};
		new ArrayArgs( myDate );
	}
}
