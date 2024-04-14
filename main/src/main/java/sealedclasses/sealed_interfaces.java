// package sealedclasses;

// tag::main[]
void main() {
	System.out.println("Nothing to see here, folks.");
}

sealed interface A1 permits A2, A3, A4 {
	void process();
}

non-sealed interface A2 extends A1 {}

interface B1 extends A2 {}		// Allowed because A2 not sealed

non-sealed interface A3 extends A1 {}

sealed interface A4 extends A1 permits B2 {}

non-sealed interface B2 extends A4 {}
// end::main[]
