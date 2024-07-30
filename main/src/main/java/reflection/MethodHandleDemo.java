package reflection;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.time.LocalDate;

public class MethodHandleDemo {
    public static void main(String[] args) throws Throwable {

// tag::main[]
        // Create a Lookup object
        MethodHandles.Lookup lup = MethodHandles.lookup();

        // First invoke LocalDate's static "of(year, month, day)" method:

        // Create a matcher for the arg list types we want to invoke
        // First the return type, then the argument types
        MethodType mt = MethodType.methodType(LocalDate.class,
			int.class, int.class, int.class);
        // Use the Lookup object to find the static method "of" in LocalDate
        MethodHandle mh = lup.findStatic(LocalDate.class, "of", mt);
        // At last, invoke the method!
        LocalDate endDate = (LocalDate) mh.invokeExact(2014, 6, 10);
        System.out.println("LocalDate from 'of' = " + endDate);

        // Second, invoke the LocalDate's instance method toString()
        // "toString()" returns a String, takes no args
        mt = MethodType.methodType(String.class);
        // Find the instance method in the LocalDate class
        mh = lup.findVirtual(LocalDate.class, "toString", mt);
        // Invoke it in the context of the LocalDate object created earlier
        String asString = (String) mh.invokeExact(endDate);
        System.out.println("LocalDate as String is " + asString);
// end::main[]
    }
}
