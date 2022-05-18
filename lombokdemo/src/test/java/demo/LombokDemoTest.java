package demo;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class LombokDemoTest {
	
	@Test
	public void test() {
		LombokDemo demo = LombokDemo.builder().id(123).name("The Eagles").build();
		assertEquals(123, demo.getId());
		assertEquals("The Eagles", demo.getName());
	}

	
}
