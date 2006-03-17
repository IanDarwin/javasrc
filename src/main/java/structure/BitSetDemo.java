package structure;

import java.util.BitSet;

/**
 * Bitset demo
 */
public class BitSetDemo {
	public static void main(String[] argv) {
		BitSet bs = new BitSet();
		bs.set(65);
		System.out.println("Bit 65 is " + bs.get(65));
	}
}
