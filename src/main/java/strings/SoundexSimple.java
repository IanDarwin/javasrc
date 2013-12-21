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
		for (int i = 0; i< names.length; i++)
			System.out.println(Soundex.soundex(names[i]) + ' ' + names[i]);
	}
}
// END main
