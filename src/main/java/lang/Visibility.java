package lang;

public class Visibility {
	final int i = 42;
	void method1() {
		// int i = 13;				// would conflict with line below
		for (int i=0; i<10; i++)	// does not corrupt object's i
			// for (int i=0; i<3; i++) // not allowed, use j
				System.out.println("i = " + i);	// prints 0, 1, ...
		System.out.println("i = " + i);		// prints 42
	}
}
