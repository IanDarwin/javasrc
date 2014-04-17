package oo;

// BEGIN main
public class EqualsDemo {
    private int int1;
    private SomeClass obj1;

    /** Constructor */
    public EqualsDemo(int i, SomeClass o) {
        int1 = i;
        if (o == null) {
            throw new IllegalArgumentException("Data Object may not be null");
        }
        obj1 = o;
    }

    /** Default Constructor */
    public EqualsDemo() {
        this(0, new SomeClass());
    }

    /** Demonstration "equals" method */
    @Override
    public boolean equals(Object o) {
        if (o == this)                    // <1> optimization
            return true;

        if (o == null)                    // <2> No object ever equals null
            return false;
        
        // Of the correct class?
        if (o.getClass() != EqualsDemo.class) // <3>
            return false;

        EqualsDemo other = (EqualsDemo)o; // OK, cast to this class

        // compare field-by-field         // <4>
        if (int1 != other.int1)           // compare primitives directly
            return false;
        if (!obj1.equals(other.obj1))     // compare objects using their equals
            return false;
        return true;
    }
}
// END main
