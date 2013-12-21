package performance;

import com.darwinsys.lang.MutableInteger;

/** Time a bunch of creates and gets through an Array */
// BEGIN main
public class Array {
	public static final int MAX = 250000;
	public static void main(String[] args) {
		System.out.println(new Array().run());
	}
	public int run() {
		MutableInteger list[] = new MutableInteger[MAX];
		for (int i=0; i<list.length; i++) {
			list[i] = new MutableInteger(i);
		}
		int sum = 0;
		for (int i=0; i<list.length; i++) {
			sum += list[i].getValue();
		}
		return sum;
	}
}
// END main
