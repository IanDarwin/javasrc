import java.io.PrintStream;
import java.util.Date;

/**
 * VarArgsDemo - show 1.5 variable arguments
 * @author ian
 * @version $Id$
 */
public class VarArgsDemo {

	public static void main(String[] args) {
		process(System.out, "Hello", "Goodbye");
		process(System.out, 42, 1066, 1776);
		process(System.out, "Foo", new Date(), new Object());
	}
	static void process(PrintStream out, Object ... args) {
		line();
		for (int i = 0; i < args.length; i++){
			out.println("Argument " + i + " is " + args[i]);
		}
	}
	static void process(PrintStream out, int ... args) {
		for (int i = 0; i < args.length; i++){
			out.println("Argument " + i + " is " + args[i]);
		}
	}
	private static void line() {
		System.out.println("--------------------------");
	}
}
