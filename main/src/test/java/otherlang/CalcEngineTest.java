package otherlang;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CalcEngineTest {
	
	ScriptEngine engine;

	@BeforeEach
	void setUp() throws Exception {
		ScriptEngineManager scriptEngineManager = new ScriptEngineManager();
		engine =  scriptEngineManager.getEngineByName("SimpleCalc");
		if (engine == null) {
			throw new IllegalStateException("Could not find engine");
		}
	}

	@Test
	void add() throws Exception {
		engine.put("i", 99);
		engine.put("j", 1);
		Object retVal = engine.eval("i j +");
		assertEquals(100, retVal, "add");
	}

	@Test
	void sub() throws Exception {
		// 7 5 - computes -2 instead of 2
		engine.put("a", 7);
		engine.put("b", 5);
		Object ret = engine.eval("a b -");
		assertEquals(+2, ret, "subtract");
	}

	@Test
	void multiply() throws Exception {
		// 7 5 - computes -2 instead of 2
		engine.put("a", 7);
		engine.put("b", 5);
		Object ret = engine.eval("a b *");
		assertEquals(35, ret, "multiply");
	}

	@Test
	void divide() throws Exception {
		// 7 5 - computes -2 instead of 2
		engine.put("a", 15);
		engine.put("b", 5);
		Object ret = engine.eval("a b /");
		assertEquals(3, ret, "divide");
	}

}
