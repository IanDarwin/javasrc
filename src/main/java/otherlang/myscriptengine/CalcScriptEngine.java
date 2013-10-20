package otherlang.myscriptengine;

import java.io.Reader;

import javax.script.AbstractScriptEngine;
import javax.script.Bindings;
import javax.script.ScriptContext;
import javax.script.ScriptEngineFactory;
import javax.script.ScriptException;

public class CalcScriptEngine extends AbstractScriptEngine {

	private ScriptEngineFactory factory;
	
	CalcScriptEngine(ScriptEngineFactory factory) {
		super();
		this.factory = factory;
	}

	@Override
	public Object eval(String script, ScriptContext context)
			throws ScriptException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object eval(Reader reader, ScriptContext context)
			throws ScriptException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Bindings createBindings() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ScriptEngineFactory getFactory() {
		return factory;
	}

}
