import java.io.IOException;

/** Demonstration of reading the MS-Windows Netscape History
 * under UNIX using DBM.java.
 * @version $Id$
 */
public class ReadHistNS {
	public static void main(String[] unused) throws IOException {
		DBM d3 = new DBM("netscape.hst");
		byte[] ba;
		for (ba = d3.firstkey(); ba != null; ba = d3.nextkey(ba)) {
			System.out.println("Key=\"" + new String(ba) + '"');
		}
	}
}
