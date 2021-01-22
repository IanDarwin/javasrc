package lang;

public class SealedClassDemo {

	public static void main(String[] args) {
		System.out.println("SealedClassDemo.main()");
	}
	
	sealed class A permits B {
		
	}

	non-sealed class B extends A {
		
	}
	
	final class C extends B { // "extends A" will not compile!
		
	}
}
