package oo;

/** Cheat on a Singleton by calling its clone method?
 */
public class SingletonCheat1 {
	public static void main(String[] args) {
		Singleton s = Singleton.getInstance();
		try {
			s.clone();	// EXPECT COMPILE ERROR - clone is protected!
		} catch (CloneNotSupportedException ex) {
			System.out.println("You can'd do it this way either: " + ex);
		}
	}
}
