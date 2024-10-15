/* Creates a "diamond inheritance" (B and C both extend from A,
 * and the main class implements both). While this is a conflict,
 * it's resolvable by use of _interfaceName_.super().method().
 */

interface A {
	void display();
}

interface B extends A {
    default void display() {
		System.out.println("In B::func()");
    }
}

interface C extends A {
    default void display() {
		System.out.println("In C::func()");
    }
}

public class InterfaceConflicts implements B, C {
    @Override
    public void display() {
        B.super.display(); // Calls display method from interface B
        C.super.display(); // Calls display method from interface C
    }
    public static void main(String[] args) {
        InterfaceConflicts main = new InterfaceConflicts();
        main.display();
    }
}
