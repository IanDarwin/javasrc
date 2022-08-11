package testing;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestInitializers {

	static MyFactory factory;
	private ClassUnderTest target;

	@BeforeAll
	public static void beforeAll() {
		System.out.println("TestInitializers.beforeAll");
		factory = new MyFactory();
	}

	@BeforeEach
	public void beforeEach() {
		System.out.println("TestInitializers.beforeEach");
		target = factory.createObject();
	}

	@Test
	public void testName() {
		System.out.println("TestInitializers.testName");
		target.setName("Ian Darwin");
		var f = target.getName();
		assertTrue(f.contains("Ian"));
	}
	
	@AfterAll
	public static void afterAll() {
		System.out.println("TestInitializers.afterAll");
		factory.close();
	}

	// Dummy classes to make the above compile
	/** World's cheesiest factory */
	static class MyFactory {
		ClassUnderTest createObject() {
			return new ClassUnderTest();
		}

		public void close() {
			// Empty, but in real code  might need to free some resources here...
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
