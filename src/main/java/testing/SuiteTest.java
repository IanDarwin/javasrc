package testing;

import junit.extensions.TestSetup;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Example of how to do one-time setUp() processing, instead of the normal
 * per-test invocations thereof (must use a slightly different method name,
 * in this case oneTimeSetUp() is used).
 */
public class SuiteTest extends TestCase {

    public static Test suite() {
    	  
        TestSuite suite = new TestSuite();
        suite.addTestSuite(SuiteTest.class);
  
        TestSetup decorator = new TestSetup(suite) {
            protected void setUp() {
                oneTimeSetUp();
            }
            protected void tearDown() {
                oneTimeTearDown();
            }
        };
        return decorator;
    }

    public static void oneTimeSetUp() {
        // one-time initialization code
    	System.out.println("Foo.oneTimeSetUp()");
    }

    @Override
    public void setUp() {
    	System.out.println("Foo.real setUp()");
    }
    
    public static void oneTimeTearDown() {
        // one-time cleanup code
    	System.out.println("Foo.oneTimeTearDown()");
    } 
    
    public void testOne() {
    	System.out.println("Foo.testOne()");
    }
    
    public void testTwo() {
    	System.out.println("Foo.testTwo()");
    }
}
