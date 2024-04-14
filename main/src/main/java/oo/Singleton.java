package oo;

/** An example of a Singleton implementation in Java, using static initialization.
 * The Singleton design pattern is described in GOF; the idea is to ensure
 * that only one instance of the class will exist in a given application.
 * @author Ian F. Darwin, http://www.darwinsys.com/
 */
// tag::main[]
public class Singleton {

    /** 
     * Static Initializer is run before class is available to code, avoiding
     * broken anti-pattern of lazy initialization in instance method.
     * For more complicated construction, could use static block initializer.
     */
    private static Singleton instance = new Singleton();

    /** A private Constructor prevents any other class from instantiating. */
    private Singleton() {
        // nothing to do this time
    }

    /** Static 'instance' method */
    public static Singleton getInstance() {
        return instance;
    }

    // other methods protected by singleton-ness would be here...

    /** A simple demo method */
    public String demoMethod() {
        return "demo";
    }
}
// end::main[]
