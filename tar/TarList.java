import java.io.*;
import java.util.*;

/**
 * Demonstrate the Tar archive lister.
 * @author Ian F. Darwin, ian@darwinsys.com
 * @version $Id$
 */
public class TarList {
	public static void main(String argv[]) throws IOException, TarException {
		//+
		TarFile tf = new TarFile("demo.tar");
		Enumeration list = tf.entries();
		while (list.hasMoreElements())
			System.out.println(list.nextElement());
		//-
	}
}
