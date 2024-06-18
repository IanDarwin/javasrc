// tag::main[]
	import java.lang.reflect.*;	// Unused, just to show import is allowed

	int x;						// instance fields allowed

	void main(String[] args) {	// instance main
		System.out.println("Hello, world");
		process(1);
	}
	void process(int n) {		// instance method
		System.out.println("Hello " + n);
	}
// end::main[]
