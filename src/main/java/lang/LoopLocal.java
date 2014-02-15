package lang;

/** "for loop" variables are local to the loop! */
public class LoopLocal {
	public static void main(String[] args) {
		for (int j = 0; j < 5; j++) { 
			System.out.println(j);
		}
		// System.out.println(j);
	}
}
