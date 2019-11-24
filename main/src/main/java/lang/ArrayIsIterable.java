package lang;

/** Do Java arrays implement Iterable?
 */
public class ArrayIsIterable {
	public static void main(String[] args) {
		// Cannot just use (args instanceof Iterable) because 
		// compiler thinks you want args[i] there.
		Class<? extends String[]> argsClass = args.getClass();
		System.out.println("Arrays implement Iterable is " +
				Iterable.class.isAssignableFrom(argsClass));
		// Answer: No, at least on JDK 1.5 and 1.6.
		for (Class<?> i : argsClass.getInterfaces()) {
			System.out.println("But it does implement " + i.getName());
		}
	}
}
