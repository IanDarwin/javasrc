import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.InputStream;

/**
 * name - purpose
 * 
 * @author ian @verion $Id$
 */
public class MediaInvoicer {

	public static void main(String[] args) {
		MediaInvoicer mi = new MediaInvoicer(System.in);
		Invoice i;
		while ((i = mi.getInvoice()) != null) {
			System.out.println(i);
		}
	}
	BufferedReader myFile;
	public MediaInvoicer(InputStream is) {
		myFile = new BufferedReader(new InputStreamReader(is));
	}

	Invoice getInvoice() {
		return null;
	}

	class Invoice {

	}
}
