import java.io.*;
import org.xml.sax.*;
import org.w3c.dom.*;
import com.sun.xml.tree.*;

/**
 * Class with code to walk a tree and convert it to MIF.
 * For now it just outputs plain text.
 * @author Ian F. Darwin, ian@darwinsys.com
 * @version $Id$
 */
public class ConvertToMif implements XmlFormWalker {
	/** The normal output writer */
	PrintWriter msg;
	/** A tree walker object for walking the tree */
	TreeWalker tw;
	/** Construct a converter object */
	ConvertToMif(Document doc, PrintWriter msg) {
		tw = new TreeWalker(doc);
		this.msg = msg;
	}
	/** Convert all the nodes in the current document. */
	public void convertAll() {
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
}
