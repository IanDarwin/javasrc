package lang;

/** Is it true that Java arrays implement Iterable?
 */
public class ArrayIsIterable {
	public static void main(String[] args) {
		Class<? extends String[]> argsClass = args.getClass();
		System.out.println(Iterable.class.isAssignableFrom(argsClass));
	}
}
