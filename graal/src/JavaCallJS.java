import java.io.*;
import java.util.stream.*;
import org.graalvm.polyglot.*;

/**
 * GraalVM polyglot: calling JavaScript from Java/
 */
public class JavaCallJS {

	public static void main(String[] args) throws IOException {

		try (BufferedReader reader = 
			new BufferedReader(new InputStreamReader(System.in));
			Context context = Context.create("js");) {
			// Remap file to correct platform endings; Java handles bad
			// line endings but other languages may not.
			String input = 
			reader.lines().collect(Collectors.joining(System.lineSeparator()));
		 
			Value parse = context.eval("js", "JSON.parse");
			Value stringify = context.eval("js", "JSON.stringify");
			Value result = stringify.execute(parse.execute(input), null, 2);
			System.out.println(result.asString());
		}
	}
}
