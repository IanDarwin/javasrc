package oo;

public class SingletonDemo {
	public static void main(String[] args) {

		// tag::main[enum-based]
		// Demonstrate the enum method:
		EnumSingleton.INSTANCE.demoMethod();
		// end::main[enum-based]

		// tag::main[code-based]
		// Demonstrate the code-based method:
		Singleton.getInstance().demoMethod();
		// end::main[code-based]
	}
}
