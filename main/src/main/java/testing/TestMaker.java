package testing;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.Map;

import com.darwinsys.lang.MutableInteger;

/**
 * TestMaker - generate a JUnit test case for an existing class.
 */
public class TestMaker {
	PrintWriter out;

	public static void main(String[] args) throws IOException {
		String fullClassName = args.length > 0 ? args[0] : "java.net.Socket";
		String pkgName = "regress";
		new File(pkgName).mkdir();
		int lastDot = fullClassName.lastIndexOf('.');
		String printableClassName = lastDot > 0 ? fullClassName.substring(lastDot + 1) : fullClassName;
		String testClassName = printableClassName + "Test";
		TestMaker gt = new TestMaker(new PrintWriter(
				new FileWriter(pkgName + "/" + testClassName + ".java")));
		gt.emitClass(fullClassName, printableClassName, testClassName);
		System.out.println("All Done");
	}
	
	/**
	 * Constructor
	 */
	public TestMaker(PrintWriter writer) {
		this.out = writer;
	}

	/**
	 * Emit all the tests for one class.
	 * @param fullClassName - the class to write tests for
	 */
	private void emitClass(String fullClassName, 
		String printableClassName, String testClassName) {

		Class<?> klass = null;
		try {
			klass = Class.forName(fullClassName);
		} catch (ClassNotFoundException e) {
			System.err.println("Class " + fullClassName + " would not load, check CLASSPATH" );
			return;
		}

		out.println("package regress;");
		out.println();
		out.println("import org.junit.Test;");
		out.println();
		out.println("/** Auto-generated TestCase for " + fullClassName);
		out.println(" */");
		out.println("public class " + testClassName + " {");
		out.println("\tpublic " + fullClassName + " target;");
		out.println("\tpublic " + testClassName + "() {");
		out.println("\t\t// TODO: initialize target...");
		out.println("\t}");
		Method[] methods = klass.getDeclaredMethods();
		for (int i = 0; i < methods.length; i++) {
			Method method = methods[i];
			int m = method.getModifiers();
			if (!Modifier.isPublic(m)) 
				continue;
			emitMethod(fullClassName, method,  m);
		}
		out.println("}");
		out.flush();
	}
	
	// Map to permute method names for overloaded methods
	Map<String,MutableInteger> emittedMap = new HashMap<String,MutableInteger>();

	/**
	 * Generate a method to test one method in the target.
	 * @param method The Method descriptor to be tested.
	 */
	private void emitMethod(String className, Method method, int modifiers) {
		String name = method.getName();
		MutableInteger n;
		if ((n = emittedMap.get(name))  == null) {
			n = new MutableInteger(0);
			emittedMap.put(name, n);
		}

		out.println("\t@Test");
		out.println("\tpublic void test" + name + n.incr() + "() throws Exception {");
		out.println("\t\t// TODO Auto-generated method stub");
		
		// Get list of arguments. Declare a local var for each.

		Class<?>[] paramTypes = method.getParameterTypes();
		for (int i = 0; i < paramTypes.length; i++) {
			Class<?> thisArgClass = paramTypes[i];
			out.print("\t\t" + thisArgClass.getName() + " arg" + i + "= ");
			if (thisArgClass.equals(boolean.class)) {
				out.print(false);
			} else if (thisArgClass.isPrimitive()) {
				out.print(0);
			} else  {
				out.print("null");
			}
			out.println(';');
		}
		
		// Invoke as either static or instance method, as appropriate.
		if (Modifier.isStatic(modifiers))
			out.print("\t\t" + className + "." + name + "(");
		else
			out.print("\t\ttarget." + name + "(");
		
		// Generate rest of method call using args declared above.
		for (int i = 0; i < paramTypes.length; i++) {
			if (i > 0)
				out.print(", ");
			out.print(" arg" + i);
		}
		out.println(");");

		out.println("\t}");
	}
}
