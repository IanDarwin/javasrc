package otherlang;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

/** Simple main program showing the CalcScriptEngine working. */
public class ScriptWithCalcDemo {

	public static void main(String[] args) throws ScriptException {
		ScriptEngineManager scriptEngineManager = new ScriptEngineManager();
		ScriptEngine engine = 
			scriptEngineManager.getEngineByName("SimpleCalc");
		if (engine == null) {
			System.err.println("Could not find engine");
			return;
		}
		Object retVal = engine.eval("print('Hello World');");
		System.out.println("Script returned " + retVal);
	}
}
