package numbers;

public class SmallIntMult {
    public static void main(String[] argv) {
        byte b = 1;
        short s = 1;
        // You may be surprised by the next line.
        short result = b * s;		// EXPECT COMPILE ERROR
    }
}
