package otherlang.calcscriptengine;

import java.io.Reader;

import javax.script.AbstractScriptEngine;
import javax.script.Bindings;
import javax.script.ScriptContext;
import javax.script.ScriptEngineFactory;
import javax.script.ScriptException;
import javax.script.SimpleBindings;

public class CalcScriptEngine extends AbstractScriptEngine {

	private ScriptEngineFactory factory;
	
	CalcScriptEngine(ScriptEngineFactory factory) {
		super();
		this.factory = factory;
	}

	@Override
	public Object eval(String script, ScriptContext context)
			throws ScriptException {
		System.out.println("CalcScriptEngine.eval():");
		System.out.println(script);
		return 42;
	}

	@Override
	public Object eval(Reader reader, ScriptContext context)
			throws ScriptException {
		System.out.println("CalcScriptEngine.eval()");
		return null;
	}

	@Override
	public Bindings createBindings() {
		Bindings ret = new SimpleBindings();
		return ret;
	}

	@Override
	public ScriptEngineFactory getFactory() {
		return factory;
	}
}
