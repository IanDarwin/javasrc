package performance;

public class InlineA {
	public static void main(String[] args) {
		InlineA o = new InlineA();
		for (int i=0; i<100000; i++) {
			o.compute();
		}
	}
	public int compute() {
		int tot=0;
		for (int i=0; i<1000; i++) {
			tot += i;
		}
		return tot;
	}
}
