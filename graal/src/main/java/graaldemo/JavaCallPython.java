package graaldemo;

// tag::main[]
import org.graalvm.polyglot.Context;
import org.graalvm.polyglot.Value;

/**
 * GraalVM polyglot: calling Python from Java.
 */
public class JavaCallPython {

	public static void main(String[] args)  {

		try (Context context = Context.create("python")) {
			Value result = context.eval("python", "2 + 2");
			int i = result.asInt();
			System.out.println(i);
		}
	}
}
// end::main[]
