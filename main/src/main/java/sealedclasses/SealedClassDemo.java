package sealedclasses;

public class SealedClassDemo {

	public static void main(String[] args) {
		System.out.println("SealedClassDemo.main()");
		System.out.println(new A().getClass());
		System.out.println(new B().getClass());
		System.out.println(new C().getClass());
	}
}

