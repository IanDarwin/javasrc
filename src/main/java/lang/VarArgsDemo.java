package lang;

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
		passThrough(System.out, "%s %s %s%n", "Foo", new Date(), new Object());
	}
	
	/** Show passing the entire varargs list on to another method
	 * @param out
	 * @param fmt
	 * @param args
	 */
	static void passThrough(PrintStream out, String fmt, Object ... args) {
		line();
		out.printf(fmt, args);
	}
	
	/** Show iterating through the varargs list.
	 * @param out
	 * @param args
	 */
	static void process(PrintStream out, Object ... args) {
		line();
		int i = 0;
		for (Object o : args){
			out.print("Argument " + ++i + " is " + o + "; ");
		}
		System.out.println();
	}
	
	/** Overload using int varargs.
	 * @param out
	 * @param args
	 */
	static void process(PrintStream out, int ... args) {
		line();
		System.out.println("int varargs");
		for (int i = 0; i < args.length; i++){
			out.print("Argument " + i + " is " + args[i] + "; ");
		}
		System.out.println();
	}
	
	/** toy, draw a line... */
	private static void line() {
		System.out.println("--------------------------");
	}
}
