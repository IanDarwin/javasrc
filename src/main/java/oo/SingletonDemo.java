package oo;

public class SingletonDemo {
	public static void main(String[] args) {

		// tag name=enum-based[]
		// Demonstrate the enum method:
		EnumSingleton.INSTANCE.demoMethod();
		// end name=enum-based[]

		// tag name=code-based[]
		// Demonstrate the code-based method:
		Singleton.getInstance().demoMethod();
		// end name=code-based[]
	}
}
