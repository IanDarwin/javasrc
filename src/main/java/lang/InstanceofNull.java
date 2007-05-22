package lang;

/** Show that "null instanceof SomeClass" returns false, not a NPE */
public class InstanceofNull {
        public static void main(String[] args) {
                try {
                        boolean match = null instanceof Object;
                        System.out.println("null instanceof Object == " + match);
                } catch (Exception t) {
                        System.err.println(
                        "OK, I admit I was wrong, null instanceof Object throws " + t);
                }
        }
}
