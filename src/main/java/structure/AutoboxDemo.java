package structure50;

public class AutoboxDemo {
	public static void main(String[] args) {
		int i = 42;
		foo(i);
	}

	public static void foo(Integer i) {
		System.out.println("Object = " + i);
	}
}
