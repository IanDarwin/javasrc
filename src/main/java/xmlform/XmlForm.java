import java.io.*;
import org.xml.sax.*;
import org.w3c.dom.*;
import com.sun.xml.tree.*;

/** Convert a simple XML file to text.
 * @author Ian Darwin, ian@darwinsys.com
 * @version $Id$
 */
public class XmlToText {
	protected Reader is;
	protected String fileName;

	static PrintWriter msg = new PrintWriter(System.out, true);

	/** Construct a converter given an input filename */
	public XmlToText(String fn) {
		fileName = fn;
	}

	/** Convert the file */
	public void convert(boolean verbose) {
	try {
		if (verbose)
			msg.println(">>>Parsing " + fileName + "...");
		// Make the document a URL so relative DTD works.
		String uri = "file:" + new File(fileName).getAbsolutePath();
		XmlDocumentBuilder  builder = new XmlDocumentBuilder();
		Parser instance = new com.sun.xml.parser.Parser();
		instance.setDocumentHandler(builder);
		builder.setParser(instance);
		builder.setDisableNamespaces(false);
		instance.parse(uri);
		XmlDocument doc = builder.getDocument();
		if (verbose)
			msg.println(">>>Walking " + fileName + "...");
		TreeWalker tw = new TreeWalker(doc);
		for (Node p = tw.getCurrent(); p != null; p = tw.getNext())
			if (p instanceof com.sun.xml.tree.XmlDocument)
				continue;	// nothing to do - structural object.
			// else if (p instanceof com.sun.xml.tree.Doctype)
			//	continue;	// ditto
			else if (p instanceof Element)
				doElement((Element)p);
			else if (p instanceof org.w3c.dom.CharacterData)
				doCData((org.w3c.dom.CharacterData)p);
			// else
			//	msg.println("IGNORING non-Element: " +
			//		p.getClass() + ':' + p.toString() + "\n" +
			//		p.getNodeValue());

		} catch (Exception ex) {
			System.err.println(ex);
		}
		if (verbose)
			msg.println(">>>Done " + fileName + "...");
	}

	protected void doElement(Element p) {
		String tag = p.getTagName().toLowerCase();
		if (tag.equals("book"))
			doBookStart(p);
		else if (tag.equals("head"))
			msg.println("<<START HEADING>>");
		else if (tag.equals("title"))
			msg.println("Book title: ");
		else if (tag.equals("author"))
			msg.println("Written by: ");
		else if (tag.equals("meta"))
			return;
		else if (tag.equals("body"))
			msg.println("<<START BODY>>");
		else if (tag.equals("ch"))
			doChapter(p);
		else if (tag.equals("sc"))
			doSection(p);
		else if (tag.equals("p"))
			doParagraph(p);
		else
			msg.println("IGNORING UNHANDLED TAG " + tag + '(' +
				p.getClass() + '@' + p.hashCode() + ')');
	}

	protected void doBookStart(Element p) {
		msg.println("<<Start of Book!>>");
	}

	protected void doChapter(Element p) {
		msg.println(">>>Start Chapter");
	}

	protected void doSection(Element p) {
		msg.println(">>>Start Section");
	}

	protected void doParagraph(Element p) {
		msg.println(">>>Start Paragraph");
	}

	protected void doCData(org.w3c.dom.CharacterData p) {
		// msg.println("CDATA@"+p.hashCode()+ ": ");
		String s = p.getData().trim();
		if (s.length() == 0)	// Sun's parser returns extra 1-space "Text"s
			return;
		msg.println(s);
	}

	public static void main(String av[]) {
		if (av.length == 0) {
			System.err.println("Usage: XmlForm file");
			return;
		}
		for (int i=0; i<av.length; i++) {
			String name = av[i];
			new XmlToText(name).convert(true);
		}
	}
}
