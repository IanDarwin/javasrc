package lang;

/** If main is not special to the compiler, you should be able to 
 * write it any way you like. And you can. But the <KBD>java</KBD>
 * command only knows to call one particular form, as you can see
 * by compiling and running this program.
 */
public class MainNotSpecial {
	public static void main() {
		System.out.println("In no-argument main");
	}
	public static void main(String[] argv) {
		System.out.println("In String[] main");
	}
	public static void main(int argc, String argv[]) {
		System.out.println("In int,String[] main");
	}
	public static void main(String argv) {
		System.out.println("In String main");
	}
}
