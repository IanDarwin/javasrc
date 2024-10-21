package graaldemo;

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
		 
			Value parse = context.eval("js", "JSON.parse");
			Value stringify = context.eval("js", "JSON.stringify");
			Value result = context.eval("js", "(2 + 2).toString()");
			System.out.println(result.asString());
		}
	}
}
