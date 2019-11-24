package performance;

import java.util.List;
import java.util.Vector;

import com.darwinsys.lang.MutableInteger;

/** Time a bunch of creates and gets through an Array */
public class ArrayVec {
	public static final int MAX = 250000;
	public static void main(String[] args) {
		System.out.println(new ArrayVec().run());
	}
	public int run() {
		List<MutableInteger> list = new Vector<>();
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
