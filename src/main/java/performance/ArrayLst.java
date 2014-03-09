package performance;

import java.util.ArrayList;

import com.darwinsys.lang.MutableInteger;

/** Time a bunch of creates and gets through an Array */
// BEGIN main
public class ArrayLst {
	public static final int MAX = 250000;
	public static void main(String[] args) {
		System.out.println(new ArrayLst().run());
	}
	public int run() {
		ArrayList<MutableInteger> list = new ArrayList<>();
		for (int i=0; i<MAX; i++) {
			list.add(new MutableInteger(i));
		}
		int sum = 0;
		for (int i=0; i<MAX; i++) {
			sum += ((MutableInteger)list.get(i)).getValue();
		}
		return sum;
	}
}
// END main
