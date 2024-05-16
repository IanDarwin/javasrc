package lang;

/* Make sure the compiler accepts European languages everywhere.
 * This file is encoded as UTF-8 and should work on modern Java.
 * But: with older Java versions and/or on MS-Windows, it
 * may require a locale to be set before it will compile!
 */
public class EuroLanguage {
	public static int número;
	public static void main(String[] args) {
		System.out.println("Acción!");
		acción(123);
		System.out.println("Número = " + número);
	}
	public static void acción(int costa) {
		System.out.println("Su tarjeta cuesta los " + costa);
		número++;
	}
}

