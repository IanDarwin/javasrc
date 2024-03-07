package oo;

/**
 * Singleton implementation in Java, abusing the enum mechanism's
 * guarantee of singletonness.
 * The Singleton design pattern is described in GoF; the idea is to ensure
 * that only one instance of the class will exist in a given application.
 * @author Ian F. Darwin, http://www.darwinsys.com/
 */
// tag::main[]
public enum EnumSingleton {

    INSTANCE;

    // instance methods protected by singleton-ness would be here...

    /** A simple demo method */
    public String demoMethod() {
        return "demo";
    }
}
// end::main[]
