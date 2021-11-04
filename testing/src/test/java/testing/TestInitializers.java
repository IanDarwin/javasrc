package testing;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TestInitializers {

	static MyFactory factory;
	ClassUnderTest target;

	@BeforeAll
	public static void initMyStaticStuff() {
		factory = new MyFactory();
	}

	@BeforeEach
	public void setUpMore() {
		target = factory.createObject();
	}

	@Test
	public void testName() {
		target.setName("Ian Darwin");
		String f = target.getName();
		assertTrue(f.contains("Ian"));
	}
	
	@AfterAll
	public static void closeDown() {
		factory.close();
	}

	// Dummy classes to make the above compile
	/** World's cheesiest factory */
	static class MyFactory {
		ClassUnderTest createObject() {
			return new ClassUnderTest();
		}

		public void close() {
			// Empty, but might need to free some resources here...
		}
	}

	/** Stand-in for an actual Class being tested */
	static class ClassUnderTest extends Object {
		String name;

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}
	}
}
// END main
