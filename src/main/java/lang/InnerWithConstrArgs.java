package lang;

public class InnerWithConstrArgs {
	public static abstract class A {
		String s;
		public abstract String getTitle();
		public A(String s) {
			this.s = s;
		}
	}
	
	public static void main(String[] args) {
		A anA = new A("Hello") {
			@Override
			public String getTitle() {
				return s;
			}			
		};
		System.out.println(anA.getTitle());
	}
}
