package structure50;

import junit.framework.TestCase;
import java.util.Date;

/**
 * JUnit test for MyStack.
 * @author ian
 */
public class MyStackTest extends TestCase {
	MyStack<String> ss;
	MyStack<Date> sd;
	
	public static void main(String[] args) {
		junit.textui.TestRunner.run(MyStackTest.class);
	}
	
	public void setUp() {
		ss = new MyStack<String>();
		sd = new MyStack<Date>();
	}
	public void testPush() {
		int i = 0; 
		do {
			ss.push("Hello #" + i++);
		} while (ss.hasRoom());
		assertEquals(i, MyStack.MAX);
	}

	public void testHasNext() {
	}

	public void testHasRoom() {
	}

	public void testPop() {
	}

}
