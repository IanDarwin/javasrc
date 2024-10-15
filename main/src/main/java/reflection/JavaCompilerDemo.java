package reflection;

import java.lang.reflect.Method;
import java.net.URI;
import java.util.List;
import java.util.concurrent.Callable;

// tag::main[]
import javax.tools.JavaCompiler;
import javax.tools.SimpleJavaFileObject;
import javax.tools.ToolProvider;

/** Demo the Java Compiler API: Create a class, compile, load, and run it.
 * Best run standalone using "java JavaCompiler.java"
 */
public class JavaCompilerDemo {
	private final static String PACKAGE = "reflection";
	private final static String CLASS = "AnotherDemo";
	private static boolean verbose;
	public static void main(String[] args) throws Exception {
		String source = "package " + PACKAGE + ";\n" +                  // <1>
			"public class " + CLASS + " {\n" +
			"\tpublic static void main(String[] args) {\n" +
			"\t\tString message = (args.length > 0 ? args[0] : \"Hi\")" + ";\n" +
			"\t\tSystem.out.println(message + \" from AnotherDemo\");\n" +
			"\t}\n}\n";
		if (verbose)
			System.out.print("Source to be compiled:\n" + source);

		JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();   // <2>
		if (compiler == null) {
			throw new IllegalStateException("No default compiler, giving up.");
		}
		Callable<Boolean> compilation = 
			compiler.getTask(null, null, null, List.of("-d","."), null, // <3>
			List.of(new MySource(CLASS, source)));
		boolean result = compilation.call();                            // <4>
		if (result) {
			System.out.println("Compiled OK");
			Class<?> c = Class.forName(PACKAGE + "." + CLASS);          // <5>
			System.out.println("Class = " + c);
			Method m = c.getMethod("main", args.getClass());            // <6>
			System.out.println("Method descriptor = " + m);
			Object[] passedArgs = { args };
			m.invoke(null, passedArgs);                                 // <7>
		} else {
			System.out.println("Compilation failed");
		}
	}
}
// end::main[]
/** Wrapper class to convert file to URL for compiler API */
class MySource extends SimpleJavaFileObject {
	final String source;
	MySource(String fileName, String source) {
		super(URI.create("string:///" + fileName.replace('.', '/') + 
				Kind.SOURCE.extension), Kind.SOURCE);
		this.source = source;
	}
	@Override
	public CharSequence getCharContent(boolean ignoreEncodingErrors) {
		return source;	
	}
}

