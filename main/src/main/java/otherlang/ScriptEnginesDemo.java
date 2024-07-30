package otherlang;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

// tag::main[]
public class ScriptEnginesDemo {

	public static void main(String[] args) throws ScriptException {
		ScriptEngineManager scriptEngineManager = new ScriptEngineManager();
		
		// Run a script in the JavaScript language
		String lang = "JavaScript";
		ScriptEngine engine = 
			scriptEngineManager.getEngineByName(lang);
		if (engine == null) {
			System.err.println("Could not find engine");
			return;
		}
		engine.eval("print(\"Hello from " + lang + "\");");
	}
}
// end::main[]
