import com.darwinsys.spdf.PDF;
import com.darwinsys.spdf.Page;
import com.darwinsys.spdf.Text;
import com.darwinsys.spdf.MoveTo;
import java.io.*;

/** trivial text test of SPDF package
 */
public class Demo {
	public static void main(String[] argv) {
		PrintWriter pout = new PrintWriter(System.out);
		PDF p = new PDF(pout);
		Page p1 = new Page(p);
		p1.add(new MoveTo(p, 100, 600));
		p1.add(new Text(p, "Hello world, live on the web."));
		p1.add(new Text(p, "Hello world, live on the web."));
		p.add(p1);
		p.setAuthor("Ian F. Darwin");
		p.writePDF();
	}
}
