import com.darwinsys.spdf.PDF;
import com.darwinsys.spdf.Page;
import java.io.*;

/** trivial text test of SPDF package
 */
public class Demo {
	public static void main(String argv[]) {
		PrintWriter pout = new PrintWriter(System.out);
		PDF p = new PDF(pout);
		Page p1 = new Page(p);
		// p1.add(new PDFText(100, 600, "hello world"));
		// p.setAuthor("Ian Darwin");
		p.writePDF();
	}
}
