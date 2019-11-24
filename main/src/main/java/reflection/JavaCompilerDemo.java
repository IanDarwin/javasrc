package reflection;

import java.lang.reflect.Method;
import java.net.URI;
import java.util.List;
import java.util.concurrent.Callable;
import javax.tools.*;

public class JavaCompilerDemo {
	public static void main(String[] args) throws Exception {
		String source = "package reflection;\n" +                       // <1>
			"public class AnotherDemo {\n" +
			"\tpublic static void main(String[] args) {\n" +
			"\t\tSystem.out.println(\"Hello from a generated class\");\n" +
			"\t}\n}\n";
		System.out.print("Source to be compiled:\n" + source);

		JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();   // <2>
		if (compiler == null) {
			throw new IllegalStateException("No default compiler, giving up.");
		Callable<Boolean> compilation = 
			compiler.getTask(null, null, null, List.of("-d","."), null, // <3>
			List.of(new MySource("AnotherDemo", source)));
		boolean result = compilation.call();							// <4>
		if (result) {
			System.out.println("Compiled OK");
			Class c = Class.forName("reflection.AnotherDemo");			// <5>
			System.out.println("Class = " + c);
			Method m = c.getMethod("main", args.getClass());			// <6>
			System.out.println("Method descriptor = " + m);
			m.invoke(null, new String[]{"Hello"});						// <7>
		} else {
			System.out.println("Compilation failed");
		}
	}

	static class MySource extends SimpleJavaFileObject {
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
}
<1> The source code that we want to compile. In real life would probably be dynamically generated.
<2> Get a reference to the default JavaCompiler object.
<3> Ask the compiler to make us a Task to do the compilation.
The "-d ." are standard +javac+ arguments.
<4> Callables can be put into an Executor (see <<XXX>>), but here we just invoke it directly.
<5> Assuming the +result+ was +true+ indicating success, we load the class with +Class.forName()+
<6> We have to find the +main()+ method in the generated class.
We re-use the +String[].class+ type from args, since all +main+ methods have the same argument.
<7> Finally, we get to invoke the main method.
