package testing;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import com.darwinsys.io.ClassSourceUtils;

/** A JUnit4 test for all the accessors in all the classes in
 * a given directory.
 * Usage: run this class from a JUnit Test Runner, with the
 * JVM argument -Dtestaccessorsdir=DDD where DDD is the path to
 * top-level directory containing the classes.
 * Note that you must have all needed classes on your classpath
 * since the classes being tested get loaded and instantiated as 
 * part of this testing.
 */
@RunWith(value=Parameterized.class)
public class CheckAccessorsDirectory {

	public static final String DIRECTORY_KEY = "accessorsClassDir";
	private Class<?> clazz;

	public CheckAccessorsDirectory(Object clazz) {
		this.clazz = (Class<?>)clazz;
	}
	
	@Test
	public void testOneClass() throws Exception {
		CheckAccessors.process(clazz);
	}
	
    /** 
	 * This method provides data to the constructor for use in tests
	 * @return the data for the constructor
	 */
    @Parameters
    public static List<Class<?>[]> data() {
		System.out.println("CheckAccessorsDirectory.data()");
		final String dirName = System.getProperty(DIRECTORY_KEY);
		// We can't @Ignore this class since it's meant to be used
		// from the main program as a test, but without the @Ignore,
		// JUnit will try to run it every time. Compromise?
		if (dirName == null) {
			System.out.printf(
				"%s: Must run with JVM arg -D%s=starting_directory",
				CheckAccessorsDirectory.class.getName(),
				DIRECTORY_KEY);
			return Collections.emptyList();
		}
		final List<Class<?>> foundClasses = ClassSourceUtils.classListFromSource(dirName);
		final int numberOfClasses = foundClasses.size();
		Class<?>[] classes = foundClasses.toArray(new Class<?>[numberOfClasses]);
		System.out.printf(
			"CheckAccessorsDirectory.data(): found %d classes%n", foundClasses.size());

    	List<Class<?>[]> results = 
    		new ArrayList<Class<?>[]>(classes.length);
    	for (Class<?> cl : classes) {
    		results.add(new Class<?>[]{cl});
    	}
		return results;
	}
}
