import java.io.*;
import org.xml.sax.*;
import org.w3c.dom.*;
import com.sun.xml.tree.*;

/**
 * Class with code to walk a tree and convert it to MML (not MIF).
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

		msg.println("<MML 1.00 -- MML produced by XmlForm>");
		msg.println("<Include \"xmlformat.mml\">");

		for (Node p = tw.getCurrent(); p != null; p = tw.getNext())
			doNode(p);
	}

	public void doNode(Node p) {
		if (p instanceof com.sun.xml.tree.XmlDocument)
			return;	// nothing to do - structural object.
		// else if (p instanceof com.sun.xml.tree.Doctype)
		//	return;	// ditto
		else if (p instanceof Element)
			doElement((Element)p);
		else if (p instanceof org.w3c.dom.CharacterData)
			doCData((org.w3c.dom.CharacterData)p);
		else
			System.err.println("IGNORING non-Element: " +
				p.getClass() + ':' + p.toString() + "\n" +
				p.getNodeValue());
	}

	protected void doElement(Element p) {
		String tag = p.getTagName().toLowerCase();
		if (tag.equals("ch")) {
			doChapter(p);
		} else if (tag.equals("sc")) {
			doSection(p);
		} else if (tag.equals("p")) {
			doParagraph(p);
		} else if (tag.equals("pr")) {
			msg.println("<HeadB>Problem");
		} else if (tag.equals("so")) {
			msg.println("<HeadB>Solution");
		} else if (tag.equals("di")) {
			msg.println("<HeadB>Discussion");
		} else if (tag.equals("code")) {
			doCode(p);
		} else if (tag.equals("example")) {
			doExample(p);
		} else
			System.err.println("IGNORING UNHANDLED TAG " + tag + '(' +
				p.getClass() + '@' + p.hashCode() + ')');
	}

	protected void doChapter(Element p) {
		msg.println("<ChapterTitle>");
	}

	protected void doSection(Element p) {
		msg.println("<HeadA>");
	}

	protected void doSubSection(Element p) {
		msg.println("<HeadB>");
	}

	protected void doParagraph(Element p) {
		msg.println("<Body>");
	}

	protected void doCode(Element p) {
		msg.println("<Code>");
	}

	protected void doCData(org.w3c.dom.CharacterData p) {
		String s = p.getData().trim();
		if (s.length() == 0)	// Sun's parser returns extra 1-space "Text"s
			return;
		msg.println(s);
	}

	protected void doExample(Element p) {
		msg.println("<Example>");
	}
}
