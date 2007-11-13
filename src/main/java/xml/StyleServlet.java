package xml;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.darwinsys.io.FileIO;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

/** Output the given XML file in XML if viewable, else in HTML.
 * Has NOT been tested since conversion to JAXP!
 */
public class StyleServlet extends HttpServlet {

	private static final long serialVersionUID = 6129378881052027382L;

	public String XML_FILE;
	public String XSL_SHEET_FILE;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
	throws IOException, ServletException {

		/** Servlet API: Web App Root/WEB-INF/web.xml contains
		 * context-param name & value elements; this retrieves 'em.
		 */
		ServletContext ctx = getServletContext();
		XML_FILE = ctx.getInitParameter("xml_file_name");
		XSL_SHEET_FILE = ctx.getInitParameter("xsl_sheet_name"); 

		String browser = request.getHeader("user-agent");
		PrintWriter out = response.getWriter();

		// At this point in time, MSIE5 is the only one that can do XML.
		if (browser.indexOf("MSIE5") != -1) {

			out.println("<?xml version=\"1.0\"?>");

			output_stylesheet_ref(out);

			output_body(XML_FILE, out);

		} else {
			// Any other browser, output HTML
			transform_into_html(out);
		}
	}

	void output_stylesheet_ref(PrintWriter out) {
		out.print("<?xml-stylesheet type=\"text/xsl\" href=\"" +
			XSL_SHEET_FILE + "\"?>");
	}

	void output_body(String XML_FILE, PrintWriter out) throws IOException {
		FileIO.copyFile(XML_FILE, out, false);
	}
	
	void transform_into_html(PrintWriter out) throws ServletException {
		try {
			// Create a transformer object
			Transformer tx = TransformerFactory.newInstance().newTransformer(
					new StreamSource(new File(XSL_SHEET_FILE))); // not 0
			
			// Use its transform() method to perform the transformation
			tx.transform(new StreamSource(new File(XML_FILE)), // not 1
					new StreamResult(out));
		} catch (Exception exc) {
			throw new ServletException(exc.toString());
		}
	}
}
