import java.io.*;
import org.xml.sax.*;
import org.w3c.dom.*;
import com.sun.xml.tree.*;
import java.util.*;

/**
 * Class with code to walk a tree and convert it to Maker Interchange
 * Format (MIF). Must be MIF (not MML) since, alas, MML loses named
 * character codes in input.
 * PARTLY IMPLEMENTED: need to make Walker do recursive descent of tree!!
 * @author Ian F. Darwin, ian@darwinsys.com
 * @version $Id$
 */
public class ConvertToMif implements XmlFormWalker {
	/** The normal output writer */
	protected PrintWriter msg;
	/** Specialized PrintWriter for use by GetMark. */
	protected StyledWriter smsg;
	/** A tree walker object for walking the tree */
	protected TreeWalker tw;
	/** A GetMark converter for source code. */
	protected GetMark gm = new GetMark();
	/** Vector used to print indented lines */
	protected Vector indents;

	/** Construct a converter object */
	ConvertToMif(Document doc, PrintWriter pw) {
		tw = new TreeWalker(doc);
		msg = new PrintWriter(pw);
		smsg = new StyledWriter(msg);
		indents = new Vector();
		indents.addElement("");
	}

	protected int indent = 0;
	protected void indent() {
		if (indent > indents.size()) {
			StringBuffer sb = new StringBuffer();
			for (int i=0; i<indent; i++) {
				sb.append(' ');
				sb.append(' ');
			}
			indents.addElement(sb.toString());
		}
		msg.print(indents.elementAt(indent>0?indent-1:0));
	}

	protected Stack tagStack = new Stack();
	protected void startTag(String tag) {
		++indent;
		indent();
		msg.println('<' + tag);
		tagStack.push(tag);
	}

	protected void endTag() {
		indent();
		msg.println('>' + " # end of " + tagStack.pop());
		indent--;
	}
	/** Convert all the nodes in the current document. */
	public void convertAll() {

		msg.println("<MIFFile 3.00 -- MIF produced by XmlForm>");

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
			pgfTag("HeadB");
			pgfString("Problem");
			endTag();
		} else if (tag.equals("so")) {
			pgfTag("HeadB");
			pgfString("Solution");
			endTag();
		} else if (tag.equals("di")) {
			pgfTag("HeadB");
			pgfString("Discussion");
			endTag();
		} else if (tag.equals("code")) {
			doCode(p);
		} else if (tag.equals("example")) {
			doExample(p);
		} else
			System.err.println("IGNORING UNHANDLED TAG " + tag + '(' +
				p.getClass() + '@' + p.hashCode() + ')');
	}

	protected void doChapter(Element p) {
		pgfTag("<ChapterTitle>");
	}

	protected void doSection(Element p) {
		pgfTag("HeadA");
	}

	protected void doSubSection(Element p) {
		pgfTag("HeadB");
	}

	protected void pgfTag(String s) {
		startTag("Para");
		indent();
		msg.println("<PgfTag `" + s + "'>");
	}

	protected void doParagraph(Element p) {
		indent();
		pgfTag("Body");
	}

	protected void doExample(Element p) {
		NamedNodeMap attrs = p.getAttributes();
		Node href;
		if ((href = attrs.getNamedItem("HREF")) == null)
			throw new IllegalArgumentException(
				"node " + p + "lacks required HREF Attribute");
		String fname = href.getNodeValue();
		msg.println("<Example>");
		try {
			fname = "/javasrc/" + fname;
			LineNumberReader is = new LineNumberReader(new FileReader(fname));
			gm.process(fname, is, smsg);
		} catch(IOException e) {
			throw new IllegalArgumentException(e.toString());
		}
	}

	protected void doCData(org.w3c.dom.CharacterData p) {
		String s = p.getData().trim();
		if (s.length() == 0)	// Sun's parser returns extra 1-space "Text"s
			return;
		pgfString(s);
	}
	protected void pgfString(String s) {
		indent();
		msg.print("<String `");
		msg.print(s);			// XXX make sb, translate special chars
		msg.println("'>");
	}

	protected void doCode(Element p) {
		msg.print("<Code>");
		NodeList nodes = p.getChildNodes();
		for (int i=0; i<nodes.getLength(); i++) {
			Node n = nodes.item(i);
			if (n instanceof CharacterData) {
				doCData((CharacterData)n);
				p.removeChild(n);
			}
		}
		msg.print("<Plain>");
	}
	/** Simply subclass PrintWriter so we don't have to modify
	 * GetMark to change the format of lines that it writes, or
	 * resort to other kluges like passing it a prefix and/or suffix.
	 */
	public class StyledWriter extends PrintWriter {
		public StyledWriter(PrintWriter p) {
			super(p, true);
		}
		public void println(String s) {
			super.println("<CellBody>" + s);
		}
	}
}
