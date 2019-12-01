package com.darwinsys.testing;

import java.util.ArrayList;
import java.util.List;

import org.junit.Ignore;
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
@Ignore // Needs custom -D on command line, disable by default
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
		if (dirName == null) {
			throw new IllegalArgumentException(
				"Must run with JVM arg -D" + DIRECTORY_KEY + "= starting directory");
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
