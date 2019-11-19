package otherlang;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;

// tag::main[]
/** Demonstrates calling from Java into Python using javax.script */
public class PythonFromJava {
	private static final String PY_SCRIPTNAME = "pythonfromjava.py";

	public static void main(String[] args) throws Exception {
		ScriptEngineManager scriptEngineManager = new ScriptEngineManager();
		
		scriptEngineManager.getEngineFactories().forEach(factory ->
			System.out.println(factory.getLanguageName()));
		
		ScriptEngine engine = scriptEngineManager.getEngineByName("python");
		if (engine == null) {
			throw new IllegalStateException("
			Could not find 'python' engine; add jython-xxx.jar to CLASSPATH");	
		}
		InputStream is =
			PythonFromJava.class.getResourceAsStream("/" + PY_SCRIPTNAME);
		if (is == null) {
			throw new IOException("Could not find file " + PY_SCRIPTNAME);
		}
		engine.eval(new InputStreamReader(is));
	}
}
// end::main[]
