
package structure;

import java.util.Collection;
import java.util.List;

// tag::main[]
public class IterableForEach {

    public static void main(String[] args) {
        Collection<String> c =                    // <1>
                List.of("One", "Two", "Three");   // <2>
        c.forEach(s -> System.out.println(s));    // <3>
    }
}
// end::main[]
