import java.io.*;
import org.xml.sax.*;
import org.w3c.dom.*;
import com.sun.xml.tree.*;
import java.util.*;

/**
 * Class with code to walk a tree and convert it to Maker Interchange
 * Format (MIF). Must make MIF (not MML) since, alas, MML loses named
 * character codes in input.
 * @author Ian F. Darwin, ian@darwinsys.com
 * @version $Id$
 */
public class ConvertToMIF implements XmlFormWalker {
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
	ConvertToMIF(Document doc, PrintWriter pw) {
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
		indent(); msg.println('<' + tag);
		tagStack.push(tag);
	}

	protected void endTag() {
		indent(); msg.println('>' + " # end of " + tagStack.pop());
		indent--;
	}

	/** Convert all the nodes in the current document. */
	public void convertAll() {

		msg.println("<MIFFile 3.00 -- MIF produced by XmlForm>");

		for (Node p = tw.getCurrent(); p != null; p = tw.getNext())
			doNode(p);
	}

	protected void doNode(Node p) {
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
		if (tag.equals("chapter")) {
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
		} else if (tag.equals("sa")) {
			pgfTag("HeadB");
			pgfString("See Also");
			endTag();
		} else if (tag.equals("code")) {
			doCode(p);
		} else if (tag.equals("example")) {
			doExample(p);
		} else if (tag.equals("runoutput")) {
			doRun(p);
		} else
			System.err.println("IGNORING UNHANDLED TAG " + tag + '(' +
				p.getClass() + '@' + p.hashCode() + ')');
	}

	protected void doChapter(Element p) {
		msg.println("# START OF CHAPTER");
	}

	protected void doSection(Element p) {
		pgfTag("HeadA");
		doChildren(p);
		endTag();
	}

	protected void doSubSection(Element p) {
		pgfTag("HeadB");
		doChildren(p);
		endTag();
	}

	protected void pgfTag(String s) {
		startTag("Para");
		startTag("Pgf");
		indent(); msg.println("<PgfTag `" + s + "'>");
		endTag();
	}

	protected void doParagraph(Element p) {
		indent();
		pgfTag("Body");
		doChildren(p);
		endTag();
	}

	protected void doExample(Element p) {
		NamedNodeMap attrs = p.getAttributes();
		Node href;
		if ((href = attrs.getNamedItem("HREF")) == null)
			throw new IllegalArgumentException(
				"node " + p + "lacks required HREF Attribute");
		String fname = href.getNodeValue();
		boolean doMarks = true;
		Node marked = attrs.getNamedItem("NOMARK");
		if (marked != null)
			doMarks = true;
	
		pgfTag("Example");
		try {
			fname = "/javasrc/" + fname;	// XX dir should be parameter
			LineNumberReader is = new LineNumberReader(new FileReader(fname));
			gm.process(fname, is, smsg);
		} catch(IOException e) {
			throw new IllegalArgumentException(e.toString());
		}
		endTag();
	}

	/** Run a java Program and capture the output.
	 * TODO use class.forName.findStaticMethod() and call its main method.
	 * (then need to be writing stdout, or dup2 stdout).
	 */
	protected void doRun(Element p) {
		NamedNodeMap attrs = p.getAttributes();
		Node myClass;
		if ((myClass = attrs.getNamedItem("CLASS")) == null)
			throw new IllegalArgumentException(
				"node " + p + "lacks required CMD Attribute");
		String className = myClass.getNodeValue();
		pgfTag("Example");
		try {
			String cmd = "java " + className;	// XX dir should be parameter
			Process proc = Runtime.getRuntime().exec(cmd);
			LineNumberReader is = new LineNumberReader(
				new InputStreamReader(proc.getInputStream()));
			gm.process(className, is, smsg);
		} catch(IOException e) {
			throw new IllegalArgumentException(e.toString());
		}
		endTag();
	}

	protected void doCData(org.w3c.dom.CharacterData p) {
		String s = p.getData().trim();
		// System.err.println("doCData: String: " + s);
		if (s.length() == 0)	// Sun's parser returns extra 1-space "Text"s
			return;
		pgfString(s);
	}

	protected void pgfString(String s) {
		indent();
		startTag("ParaLine");
		mifString("String", s);
		endTag();
	}

	protected void doCode(Element p) {
		msg.print("<Literal>");
		doChildren(p);
		msg.print("<Plain>");
	}
	protected void doChildren(Element p) {
		NodeList nodes = p.getChildNodes();
		int numElem = nodes.getLength();
		// System.err.println("Element has " + numElem + " children");
		for (int i=0; i<numElem; i++) {
			Node n = nodes.item(i);
			if (n == null) {
				continue;
			}
			// System.err.println("NODE " + n.getClass());
			if (n instanceof CharacterData) {
				// System.err.println("\tCDATA");
				doCData((CharacterData)n);
				p.removeChild(n);
			} else if (n instanceof Element) {
				// System.err.println("\tELEMENT");
				doChildren((Element)n);
				p.removeChild(n);
			} else
				System.err.println( "Warning: unhandled child node " +
					n.getClass());
		}
	}

	/** Do the minumum needed to make "line" a valid MIF string. */
	protected void mifString(String tag, String line) {
		// Make new, big enough for translations
		StringBuffer b = new StringBuffer(line.length() * 2);
		b.append('<');
		b.append(tag);
		b.append(' ');
		b.append('`');

		// Process each character.
		for (int i=0; i<line.length(); i++) {
			char c = line.charAt(i);
			switch (c) {
			case '\\':	b.append("\\"); break;
			case ' ':	b.append(' '); break;
			case '\t':	b.append("\t"); break;
			case '\'':	b.append("\\xd5 "); break;
			case '<':	b.append("\\<"); break;
			case '>':	b.append("\\>"); break;
			default:	b.append(c); break;
			}
		}
		b.append(' ');
		b.append('\'');
		b.append('>');
		indent(); msg.println(b.toString());
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
