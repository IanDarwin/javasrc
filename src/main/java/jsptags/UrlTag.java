package jsptags;

import javax.servlet.jsp.*;
import javax.servlet.jsp.tagext.*;
import java.io.*;
import java.net.URL;

/** JSP Custom Tag to get the contents of an arbitrary URL.
 * Not sure how this differs from jsp:include, but here it is anyway.
 */

public class UrlTag extends TagSupport {

	private static final long serialVersionUID = -5924248057296762557L;
	public String theURL;

	public void setURL(String u) {
		theURL = u;
	}

	public int doStartTag() throws JspException {
		if (theURL == null) {
			throw new JspException(
			"Required attribute URL was not specified");
		}
		try {
			URL u = new URL(theURL);
			InputStream i = u.openStream();
			BufferedReader bs = new BufferedReader(
				new InputStreamReader(i));

			JspWriter out = pageContext.getOut();
			String line;
			while ((line = bs.readLine()) != null) {
				out.println(line);
			}
		} catch (Exception ex) {
			throw new JspException(ex.toString());
		}
		return SKIP_BODY;
	}
}
