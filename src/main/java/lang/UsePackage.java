package lang;

import com.darwinsys.ianstuff.*;

/** Demo program using classes from a package. */
public class UsePackage {
	public static void main(String[] argv) {
		UPX x = new UPX();
		UPY y = new UPY();
		System.out.println("We have constructed two objects:");
		System.out.println(x);
		System.out.println(y);
	}
}
