package performance;

public final class InlineC {
	public static void main(String[] args) {
		InlineC o = new InlineC();
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
