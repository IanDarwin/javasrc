package reflection;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

@Retention(RetentionPolicy.RUNTIME)
@interface MyTestAnnotation {
	// empty
}

@MyTestAnnotation
class PluginsViaAnnotationsTest {

	@BeforeEach
	void setUp() throws Exception {
	}

	@Test @MyTestAnnotation
	void findAnnotatedClasses() throws Exception {
		List<Class<?>> ret = 
			PluginsViaAnnotations.findAnnotatedClasses("reflection", MyTestAnnotation.class);
		assertEquals(1, ret.size());
		assertSame(PluginsViaAnnotationsTest.class, ret.getFirst());
	}

	@Test
	void findClassesWithAnnotatedMethods() throws Exception {
		List<Class<?>> ret = 
				PluginsViaAnnotations.findClassesWithAnnotatedMethods("reflection", MyTestAnnotation.class);
			assertEquals(1, ret.size());
			assertSame(PluginsViaAnnotationsTest.class, ret.getFirst());
	}

}
