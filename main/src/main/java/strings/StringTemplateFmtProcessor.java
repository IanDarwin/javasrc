import java.util.Iterator;

// tag::main[]
import static java.util.FormatProcessor.FMT;

/**
 * Demonstrate the creation and use of a fairly basic custom StringTemplate.Processor,
 * in particular, the FMT processor described in https://bugs.openjdk.org/browse/JDK-8273943
 * as "another standard processor" but never implemented for Java 21.
 * N.B. The Preview StringTemplate is likely to be pulled out of Java 23!!
 */
void main() {
var MYFMT = StringTemplate.Processor.of((StringTemplate st) -> {
    StringBuilder sb = new StringBuilder();
	// The constructed StringTemplate has two List fields,
	// List<String> fragments (the parts not in \{...}) and List<Object> values.
    var fragments = st.fragments().iterator();
    for (Object value : st.values()) {
        sb.append(String.format(fragments.next(), value));
    }
	sb.append(fragments.next());	// any leftover portion.
    return sb.toString();
});

var user = "Ian";

// Using my re-implementation of FMT:
System.out.println(
    MYFMT."Hello %s\{user}, Math.PI to 5 decimal places is %7.5f\{Math.PI}.");

// Now using the built-in FMT:
System.out.println(
    FMT."Hello %s\{user}, Math.PI to 5 decimal places is %7.5f\{Math.PI}.");
}
// end::main[]
