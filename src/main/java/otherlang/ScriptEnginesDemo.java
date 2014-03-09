package otherlang;

import java.util.List;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineFactory;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

// BEGIN main
public class ScriptEnginesDemo {

	public static void main(String[] args) throws ScriptException {
		ScriptEngineManager scriptEngineManager = new ScriptEngineManager();
		
		// Print list of supported languages
		scriptEngineManager.getEngineFactories().forEach(fact ->
			System.out.println(fact.getLanguageName()));
		
		// Run a script in the JavaScript language
		String lang = "ECMAScript";
		ScriptEngine engine = 
			scriptEngineManager.getEngineByName(lang);
		if (engine == null) {
			System.err.println("Could not find engine");
			return;
		}
		engine.eval("print(\"Hello from " + lang + "\");");
	}
}
// END main
