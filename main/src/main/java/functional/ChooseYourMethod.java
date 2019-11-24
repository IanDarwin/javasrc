package functional;


/**
 * Using Method References to choose given methods
 * @author Ian Darwin
 */
public class ChooseYourMethod {

	static final Runnable[] methods = {
			ChooseYourMethod::method1,
			ChooseYourMethod::method2,
	};

	public static void main(String[] args) {
		/** Having them as Runnable method references,
		 * we can only call them under the name run();
		 * the original name (method1 etc) are not stored
		 * anyplace that I can see.
		 */
		for (Runnable r : methods) {
			r.run();
		}
		Runnable r = methods[0];
		r.run();
	}

	static void method1() {
		System.out.println("ChooseYourMethod.method1()");
	}
	
	static void method2() {
		System.out.println("ChooseYourMethod.method2()");
	}
}
