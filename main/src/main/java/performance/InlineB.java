package performance;

public class InlineB {
	public static void main(String[] args) {
		InlineB o = new InlineB();
		for (int i=0; i<100000; i++) {
			o.compute();
		}
	}
	public final int compute() {
		int tot=0;
		for (int i=0; i<1000; i++) {
			tot += i;
		}
		return tot;
	}
}
