interface Apples {
	abstract void pick();
}

class Ttt implements Apples {
	public void pick() {
		System.out.println("Picked!");
	}
}

class InterfaceInherit extends Ttt {
	public static void main(String a[]) {
		new InterfaceInherit().run();
	}
	void run() {
		if (this instanceof T2)
		System.out.println("T2 is T2");
		if (this instanceof T)
		System.out.println("T2 is T");
		if (this instanceof Apples)
		System.out.println("T2 is a rotten Apple!!");

		// now the magic
		pick();
	}
}
