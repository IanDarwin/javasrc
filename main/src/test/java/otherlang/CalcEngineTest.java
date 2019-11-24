package otherlang;

import static org.junit.Assert.assertEquals;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;

import org.junit.Before;
import org.junit.Test;

public class CalcEngineTest {
	
	ScriptEngine engine;

	@Before
	public void setUp() throws Exception {
		ScriptEngineManager scriptEngineManager = new ScriptEngineManager();
		engine =  scriptEngineManager.getEngineByName("SimpleCalc");
		if (engine == null) {
			throw new IllegalStateException("Could not find engine");
		}
	}

	@Test
	public void testAdd() throws Exception {
		engine.put("i", 99);
		engine.put("j", 1);
		Object retVal = engine.eval("i j +");
		assertEquals("add", 100, retVal);
	}
	
	@Test
	public void testSub() throws Exception {
		// 7 5 - computes -2 instead of 2
		engine.put("a", 7);
		engine.put("b", 5);
		Object ret = engine.eval("a b -");
		assertEquals("subtract", +2, ret);
	}
	
	@Test
	public void testMultiply() throws Exception {
		// 7 5 - computes -2 instead of 2
		engine.put("a", 7);
		engine.put("b", 5);
		Object ret = engine.eval("a b *");
		assertEquals("multiply", 35, ret);
	}
	
	@Test
	public void testDivide() throws Exception {
		// 7 5 - computes -2 instead of 2
		engine.put("a", 15);
		engine.put("b", 5);
		Object ret = engine.eval("a b /");
		assertEquals("divide", 3, ret);
	}

}
