package lang.loops;

/** Compile this program and dump the compiled bytecode
 * with "javap -c" or equivalent; you will be enlightened
 * as to the true nature of while loops compared to for loops.
  */
class Loops {	// EXPECT WARNING ABOUT NAME

	 public static void main(String[] args) {
	 		int i = 0;
	 		while (i<10) {
	 			System.out.println(i);
	 			i++;
	 		}
	}
}
