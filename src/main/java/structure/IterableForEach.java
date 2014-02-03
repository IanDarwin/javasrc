package structure;

import java.util.Arrays;
import java.util.Collection;

// BEGIN main
public class IterableForEach {

    public static void main(String[] args) {
        Collection<String> c =                        // <1>
                Arrays.asList("One", "Two", "Three"); // <2>
        c.forEach(s -> System.out.println(s));        // <3>
    }
}
// END main
