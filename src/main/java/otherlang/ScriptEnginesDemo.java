package scripting;

import java.util.List;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineFactory;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public class ScriptEnginesDemo {

	public static void main(String[] args) throws ScriptException {
		ScriptEngineManager scriptEngineManager = new ScriptEngineManager();
		
		// Get list of supported languages
		List<ScriptEngineFactory> engineFactories = 
			scriptEngineManager.getEngineFactories();
		for (ScriptEngineFactory fact : engineFactories) {
			System.out.println(fact.getLanguageName());
		}
		
		// Run a script in the default language
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
