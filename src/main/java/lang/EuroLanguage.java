/* Make sure the compiler accepts European languages everywhere.
 * If you rename the class to, say, Español, many runtimes will be confused.
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

