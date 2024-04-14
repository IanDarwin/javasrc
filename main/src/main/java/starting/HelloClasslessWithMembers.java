// tag::main[]
	import java.lang.reflect.*;	// Unused, just to show import is allowed

	int x;						// instance fields

	void main(String[] args) {	// instance main
		System.out.println("Hello, world");
		process(1);
	}
	void process(int x) {		// instance method
		System.out.println("Hello " +x);
	}
// end::main[]
