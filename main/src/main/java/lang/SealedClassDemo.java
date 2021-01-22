package lang;

public class SealedClassDemo {

	public static void main(String[] args) {
		System.out.println("SealedClassDemo.main()");
	}
	
	sealed class A {
		
	}

	non-sealed class B extends A {
		
	}
}
