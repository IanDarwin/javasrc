import javax.servlet.*;
import javax.servlet.http.*;
import java.net.*;
import java.io.*;
import org.apache.xalan.xslt.*;

/** Output the given XML file in XML if viewable, else in HTML. */
public class StyleServlet extends HttpServlet {

	public String XML_FILE;
	public String SHEET_FILE;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
	throws IOException, ServletException {

		/** Servlet API 2.1: Web App Root/WEB-INF/web.xml includes
		 * context-param name & value elements; this retrieves 'em.
		 */
		ServletContext ctx = getServletContext();
		String value = ctx.getInitParameter("name");
		XML_FILE = ctx.getInitParameter("xml_file_name");
		SHEET_FILE = ctx.getInitParameter("xsl_sheet_name"); 

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
			SHEET_FILE + "\"?>");
	}

	void transform_into_html(PrintWriter out) throws ServletException {

		try {
			XSLTProcessor myProcessor = XSLTProcessorFactory.getProcessor();
			XSLTInputSource xmlSource = new XSLTInputSource(XML_FILE);
			XSLTInputSource xslStylesheet = new XSLTInputSource(SHEET_FILE);
			XSLTResultTarget xmlOutput = new XSLTResultTarget(out);
			myProcessor.process(xmlSource, xslStylesheet, xmlOutput);
		}
		catch (org.xml.sax.SAXException exc) {
			throw new ServletException("XML error: " + exc.toString());
		}
		catch (Exception exc) {
			throw new ServletException(exc.toString());
		}
	}

	void output_body(String XML_FILE, PrintWriter out) {
		com.darwinsys.util.FileIO.copy(XML_FILE, out);
	}
}
