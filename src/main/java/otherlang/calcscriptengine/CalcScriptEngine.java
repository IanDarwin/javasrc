package otherlang.calcscriptengine;

import java.io.Reader;
import java.util.Stack;
import java.util.StringTokenizer;

import javax.script.AbstractScriptEngine;
import javax.script.Bindings;
import javax.script.ScriptContext;
import javax.script.ScriptEngineFactory;
import javax.script.ScriptException;
import javax.script.SimpleBindings;

// BEGIN main
public class CalcScriptEngine extends AbstractScriptEngine {

	private ScriptEngineFactory factory;
	
	CalcScriptEngine(ScriptEngineFactory factory) {
		super();
		this.factory = factory;
	}

	@Override
	public Object eval(String script, ScriptContext context)
			throws ScriptException {
		System.out.println("CalcScriptEngine.eval(): Running: " + script);
		Stack<Integer> stack = new Stack<>();
		StringTokenizer st = new StringTokenizer(script);
		while (st.hasMoreElements()) {
			String tok = st.nextToken();
			if (tok.equals("+")) {
				return stack.pop() + stack.pop();
			}
			if (tok.equals("-")) {
				final Integer tos = stack.pop();
				return stack.pop() - tos;
			}
			if (tok.equals("*")) {
				return stack.pop() * stack.pop();
			}
			if (tok.equals("/")) {
				final Integer tos = stack.pop();
				return stack.pop() / tos;
			}
			// else ... check for other operators
			// If nothing else, must be a name. get and stack its value
			stack.push((Integer) context.getAttribute(tok));
		}
		return 0;
	}

	@Override
	public Object eval(Reader reader, ScriptContext context)
			throws ScriptException {
		System.out.println("CalcScriptEngine.eval()");
		// should read the file into a String, then
		// return eval(scriptString, context);
		throw new IllegalStateException("eval(Reader) not written yet");
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
// END main
