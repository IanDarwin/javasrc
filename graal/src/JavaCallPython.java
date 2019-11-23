import java.io.*;
import java.util.stream.*;
import org.graalvm.polyglot.*;

/**
 * GraalVM polyglot: calling Python from Java/
 */
public class JavaCallPython {

	public static void main(String[] args) throws java.io.IOException {

		 try (Context context = Context.create("jython")) {
			Value result = context.execute("2 + 2");
			System.out.println(result.asString());
		}
	}
}
