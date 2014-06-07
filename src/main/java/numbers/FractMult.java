package numbers;

/** Compute the value of 2/3 of 5 */
// BEGIN main
public class FractMult {
    public static void main(String[] u) {

        double d1 = 0.666 * 5;  // fast but obscure and inaccurate: convert
        System.out.println(d1); // 2/3 to 0.666 in programmer's head

        double d2 = 2/3 * 5;    // wrong answer - 2/3 == 0, 0*5 = 0
        System.out.println(d2);

        double d3 = 2d/3d * 5;  // "normal"
        System.out.println(d3);

        double d4 = (2*5)/3d;   // one step done as integers, almost same answer
        System.out.println(d4);

        int i5 = 2*5/3;         // fast, approximate integer answer
        System.out.println(i5);
    }
}
// END main
