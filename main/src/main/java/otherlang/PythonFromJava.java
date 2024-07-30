package otherlang;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.script.Bindings;
import javax.script.ScriptContext;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;

// tag::main[]
/**
 * Scripting demo using Python (jython) to get a Java variable, print, and change it.
 * @author Ian Darwin
 */
public class PythonFromJava {
	private static final String PY_SCRIPTNAME = "pythonfromjava.py";

	public static void main(String[] args) throws Exception {
		ScriptEngineManager scriptEngineManager = new ScriptEngineManager();

		ScriptEngine engine = scriptEngineManager.getEngineByName("python");
		if (engine == null) {
            System.out.println("" +
					"Could not find 'python' engine; add its JAR to CLASSPATH");
			System.exit(1);
		}

		final Bindings bindings = engine.getBindings(ScriptContext.ENGINE_SCOPE);
		bindings.put("meaning", 42);
		
		// Let's run a python script stored on disk (well, on classpath):
		InputStream is =
			PythonFromJava.class.getResourceAsStream("/" + PY_SCRIPTNAME);
		if (is == null) {
			throw new IOException("Could not find file " + PY_SCRIPTNAME);
		}
		engine.eval(new InputStreamReader(is));
		System.out.println("Java: Meaning of life is now " + bindings.get("meaning"));
	}
}
// end::main[]
