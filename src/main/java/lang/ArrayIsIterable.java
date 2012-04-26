package lang;

/** Is it true that Java arrays implement Iterable?
 */
public class ArrayIsIterable {
	public static void main(String[] args) {
		// Cannot just use (args instanceof Iterable) b/c compiler thinks you want args[i] there.
		Class<? extends String[]> argsClass = args.getClass();
		System.out.println(Iterable.class.isAssignableFrom(argsClass));
		// Answer: No, at least on JDK 1.5 and 1.6.
	}
}
