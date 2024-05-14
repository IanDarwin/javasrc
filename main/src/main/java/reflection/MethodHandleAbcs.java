// package reflection;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.time.LocalDate;

/** The simple ABCs of using MethodHandles */
void main() throws Throwable {

	// tag::main[]
    MethodHandles.Lookup lookup = MethodHandles.lookup();
    MethodType mt = MethodType.methodType(String.class, int.class, int.class);
    MethodHandle mh = lookup.findVirtual(String.class, "substring", mt);
    String s = (String) mh.invokeExact("Antidisestablishmentrianism", 7, 20);
    System.out.println(s);  // prints "establishment"
	// end::main[]
}
