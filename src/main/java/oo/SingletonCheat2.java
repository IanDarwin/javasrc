package oo;

/** Cheat on a Singleton by cloning it? */
public class SingletonCheat2 extends Singleton { // EXPECT COMPILE ERROR
	public Object clone() {
		Object n;
		try {
			n = super.clone();
		} catch (CloneNotSupportedException ex) {
			System.out.println("caught it now! " + ex);
		}
		return n;
	}

	public static void main(String[] args) {
		SingletonCheat2 s1 = (SingletonCheat2)SingletonCheat2.getInstance();
		System.out.println(s1);

		Object s2 = s1.clone();
		System.out.println(s2);
	}
}
