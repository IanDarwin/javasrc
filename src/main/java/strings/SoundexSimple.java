package strings;

/** Simple demonstration of Soundex.  */
// BEGIN main
public class SoundexSimple {

	/** main */
	public static void main(String[] args) { 
		String[] names = {
			"Darwin, Ian",
			"Davidson, Greg",
			"Darwent, William",
			"Derwin, Daemon"
		};
		for (String name : names) {
			System.out.println(Soundex.soundex(name) + ' ' + name);
		}
	}
}
// END main
