package otherlang.calcscriptengine;

import java.util.ArrayList;
import java.util.List;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineFactory;

// BEGIN main
public class CalcScriptEngineFactory implements ScriptEngineFactory {

	private static final String THY_NAME = "SimpleCalc";

	@Override
	public String getEngineName() {
		return THY_NAME;
	}

	@Override
	public String getEngineVersion() {
		return "0.1";
	}
	
	@Override
	public String getLanguageName() {
		return THY_NAME;
	}


	@Override
	public List<String> getExtensions() {
		ArrayList<String> ret = new ArrayList<>(1);
		ret.add("calc");
		return ret;
	}

	@Override
	public List<String> getMimeTypes() {
		ArrayList<String> ret = new ArrayList<String>(0);
		return ret;
	}

	@Override
	public List<String> getNames() {
		ArrayList<String> ret = new ArrayList<String>(1);
		ret.add(THY_NAME);
		return ret;
	}

	@Override
	public String getLanguageVersion() {
		return "0.1";
	}

	@Override
	public Object getParameter(String key) {
		switch(key) {
		case ScriptEngine.ENGINE:
			return getEngineName();
		case ScriptEngine.ENGINE_VERSION:
			return getEngineVersion();
		case ScriptEngine.LANGUAGE:
			return getLanguageName();
		case ScriptEngine.LANGUAGE_VERSION:
			return getLanguageVersion();
		default:
			throw new IllegalArgumentException("Unknown parameter " + key);
		}
	}

	@Override
	public String getMethodCallSyntax(String obj, String m, String... args) {
		// TODO Hope this isn't needed.
		return null;
	}

	@Override
	public String getOutputStatement(String toDisplay) {
		return toDisplay;
	}

	@Override
	public String getProgram(String... statements) {
		return statements.toString();
	}

	@Override
	public ScriptEngine getScriptEngine() {
		return new CalcScriptEngine(this);
	}

}
// END main
