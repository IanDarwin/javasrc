package servlet.pdfcouponservlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import spdf.MoveTo;
import spdf.PDF;
import spdf.Page;
import spdf.Text;

/** Simple PDF-based Coupon Printer Servlet
 * @author Ian Darwin
 * @version $Id$
 */
public class PDFCouponServlet extends HttpServlet {
	public void doGet(HttpServletRequest request,
		HttpServletResponse response) throws IOException {

		PrintWriter out = response.getWriter();
		response.setContentType("application/pdf");

		// Tell browser to try to display inline, but if not,
		// to save under the given filename.
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

		// Write the PDF file page
		p.writePDF();
	}
}
