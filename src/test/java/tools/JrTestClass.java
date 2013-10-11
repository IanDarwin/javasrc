package tools;

/** This is a key test class for jr. Do not delete! */
public class JrTestClass {
	public static void main(String[] args) {
		System.out.println("SUCCESS! Program t compiled and run.");
		for (int i = 0; i < args.length; i++) {
			System.out.println("JrTest arg[" + i + "] = " + args[i]);
		}
	}
	static {
		System.out.println("Class JrTest.clinit<>");
	}
}
