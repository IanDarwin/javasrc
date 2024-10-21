package graaldemo;

// tag::main[]
import java.io.*;
import java.util.stream.*;
import org.graalvm.polyglot.Context;
import org.graalvm.polyglot.Value;

/**
 * GraalVM polyglot: calling Python from Java.
 */
public class JavaCallPython {

	public static void main(String[] args) throws IOException {

		 try (Context context = Context.create("python")) {
			Value result = context.eval("python", "str(2 + 2)");
			System.out.println(result.asString());
		}
	}
}
// end::main[]
