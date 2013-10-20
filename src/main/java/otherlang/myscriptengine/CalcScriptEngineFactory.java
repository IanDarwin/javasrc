package otherlang.myscriptengine;

import java.util.ArrayList;
import java.util.List;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineFactory;

public class CalcScriptEngineFactory implements ScriptEngineFactory {

	@Override
	public String getEngineName() {
		return "Calc";
	}

	@Override
	public String getEngineVersion() {
		return "0.1";
	}

	@Override
	public List<String> getExtensions() {
		return new ArrayList<String>(0);
	}

	@Override
	public List<String> getMimeTypes() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> getNames() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getLanguageName() {
		return "SimpleCalc";
	}

	@Override
	public String getLanguageVersion() {
		return "0.1";
	}

	@Override
	public Object getParameter(String key) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getMethodCallSyntax(String obj, String m, String... args) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getOutputStatement(String toDisplay) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getProgram(String... statements) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ScriptEngine getScriptEngine() {
		return new CalcScriptEngine(this);
	}

}
