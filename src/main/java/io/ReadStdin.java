/**
 * Read from Standard Input
 * @author	Ian F. Darwin, ian@darwinsys.com
 * @version 	$Id$
 */
public class ReadStdin {
	/** Simple test case */
	public static void main(String ap[]) {
		//+
		int b = 0;
		try {
			b = System.in.read();
		} catch (Exception e) {
			System.out.println("Caught " + e);
		}
		System.out.println("Read this data: " + (char)b);
		//-
	}
}
