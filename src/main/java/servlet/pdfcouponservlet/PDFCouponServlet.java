import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import com.darwinsys.spdf.PDF;
import com.darwinsys.spdf.Page;
import com.darwinsys.spdf.Text;
import com.darwinsys.spdf.MoveTo;

/** Simple PDF-based Coupon Printer Servlet
 * @author Ian Darwin
 * @version $Id$
 */
public class PDFCouponServlet extends HttpServlet {
	public void doGet(HttpServletRequest request,
		HttpServletResponse response) throws IOException {

		PrintWriter out = response.getWriter();
		response.setContentType("application/pdf");
		response.setHeader("Content-disposition",
			"inline; filename=\"MyCoupon.pdf\"");

		PDF p = new PDF(out);
		Page p1 = new Page(p);
		p1.add(new MoveTo(p, 100, 600));
		p1.add(new Text(p, 
			"This coupon good for one free coffee in the student lounge."));
		String name = request.getParameter("name");
		if (name == null)
			name = "unknown user";
		p1.add(new Text(p,
			"Printed for the exclusive use of " + name));
		p1.add(new Text(p,
			"by Ian Darwin's PDFCoupon Servlet and DarwinSys SPDF software"));
		p1.add(new Text(p, "at " + new Date().toString()));
		p.add(p1);
		p.setAuthor("Ian F. Darwin");
		p.writePDF();
	}
}
