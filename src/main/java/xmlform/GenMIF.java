import java.io.*;
import org.xml.sax.*;
import org.w3c.dom.*;
import com.sun.xml.tree.*;
import java.lang.reflect.*;
import java.util.*;

/**
 * Class with code to walk a tree and convert it to Maker Interchange
 * Format (MIF). Must make MIF (not MML) since, alas, MML loses named
 * character codes in input.
 * <P>
 * Along the way, we do some book-specific things, like running
 * another Java class and grabbing the output back into here.
 *
 * @author Ian F. Darwin, ian@darwinsys.com
 * @version $Id$
 */
public class GenMIF implements XmlFormWalker {
	/** The normal output writer */
	protected PrintStream msg;
	/** Specialized PrintStream for use by GetMark. */
	protected StyledPrintStream smsg;
	/** A tree walker object for walking the tree */
	protected TreeWalker tw;
	/** A GetMark converter for source code. */
	protected GetMark gm = new GetMark();
	/** Vector used to print indented lines */
	protected Vector indents;

	/** Construct a converter object */
	GenMIF(Document doc, PrintStream pw) {
		tw = new TreeWalker(doc);
		msg = new PrintStream(pw);
		smsg = new StyledPrintStream(msg);
		// Reassign System.out to go there as well, so when we
		// run other main classes, their output gets grabbed.
		System.setOut(smsg);
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
		//
		// STRUCTURE TAGS
		//
		if (tag.equals("chapter")) {
			doChapter(p);
		//
		// PARAGRAPH TAGS
		//
		} else if (tag.equals("chaptertitle")) {
			doParagraph("ChapterTitleLeft", p);
		} else if (tag.equals("sc")) {
			doParagraph("HeadA", p);
		} else if (tag.equals("ss")) {
			doParagraph("HeadB", p);
		} else if (tag.equals("p")) {
			doParagraph("Body", p);
		} else if (tag.equals("pr")) {
			makeUpParagraph("HeadB", "Problem");
		} else if (tag.equals("so")) {
			makeUpParagraph("HeadB", "Solution");
		} else if (tag.equals("di")) {
			makeUpParagraph("HeadB", "Discussion");
		} else if (tag.equals("sa")) {
			makeUpParagraph("HeadB", "See Also");
		} else if (tag.equals("code")) {
			doCode(p);
		} else if (tag.equals("example")) {
			doExample(p);
		} else if (tag.equals("runoutput")) {
			doRun(p);
		//
		// STYLE TAGS
		//
		} else if (tag.equals("literal")) {	// code
			//
		} else if (tag.equals("bt")) {	// book title
			//
		} else
			System.err.println("IGNORING UNHANDLED TAG " + tag + '(' +
				p.getClass() + '@' + p.hashCode() + ')');
	}

	protected void doChapter(Element p) {
		msg.println("# START OF CHAPTER");
	}

	protected void pgfTag(String s) {
		startTag("Para");
		startTag("Pgf");
		indent(); msg.println("<PgfTag `" + s + "'>");
		endTag();	// end of Pgf, not of Para!
	}

	/** Generate a paragraph from the input */
	protected void doParagraph(String tag, Element p) {
		indent(); pgfTag(tag);
		doChildren(p);
		endTag();
	}

	/** Synthesize a paragraph when we know its content. */
	protected void makeUpParagraph(String tag, String contents) {
		indent(); pgfTag(tag);
		if (contents != null)
			pgfString(contents);
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
	
		makeUpParagraph("ExampleLabel", null);
		makeUpParagraph("ExampleTitle", fname);

		// Each line of output from gm.process() is a separate Para!
		try {
			fname = System.getProperty("codedir", ".") + '/' + fname;	
			LineNumberReader is = new LineNumberReader(new FileReader(fname));
			gm.process(fname, is, smsg);
		} catch(IOException e) {
			throw new IllegalArgumentException(e.toString());
		}
	}

	/** Run a java class' Main Program and capture the output.
	 */
	protected void doRun(Element p) {
		NamedNodeMap attrs = p.getAttributes();
		Node myClass;
		if ((myClass = attrs.getNamedItem("CLASS")) == null)
			throw new IllegalArgumentException(
				"node " + p + "lacks required CMD Attribute");
		String className = myClass.getNodeValue();

		// makeUpParagraph("Example", "Example XX: " + className);

		try {
			// First, find the class.
			Class c = Class.forName(className);

			// Create a dummy argv to pass it.
			String[] argv = new String[0];

			// Create the array of Argument Types
			Class[] argTypes = {
				argv.getClass(),	// array is Object!
			};

			// Now find the method
			Method m = c.getMethod("main", argTypes);

			// Create the actual argument array
			Object passedArgv[] = { argv };

			// Now invoke the method.
			System.err.println("Invoking " + m + "...");
			m.invoke(null, passedArgv);

		} catch (Exception e) {
			System.err.println(e);
		}
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
		mifString(s);
		endTag();
	}

	protected void doCode(Element p) {
		indent();
		pgfTag("Code");
		doChildren(p);
		endTag();
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
	protected void mifString(String line) {
		// Make new, big enough for translations
		StringBuffer b = new StringBuffer(line.length() * 2);
		b.append('<');
		b.append("String");	// maybe parameterize?
		b.append(' ');
		b.append('`');

		// Process each character.
		for (int i=0; i<line.length(); i++) {
			char c = line.charAt(i);
			switch (c) {
			case '\\':	b.append("\\"); break;
			case ' ':	b.append(' '); break;
			case '\t':	b.append("\\t"); break;
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

	/** Simply subclass PrintStream so we don't have to modify
	 * GetMark to change the format of lines that it writes, or
	 * resort to other kluges like passing it a prefix and/or suffix.
	 * <P>
	 * The goal is to make each LINE of output be a separate paragraph,
	 * since that's how Frame does Tables, and since O'Reilly uses
	 * Frame Tables for multi-line code examples.
	 * <P>
	 * Note that we never actually write anything to the StyledPrintStream's
	 * internal buffer: its println() method indirectly writes to msg.
	 * This is an example of "subclassing for indirect effect".
	 */
	public class StyledPrintStream extends PrintStream {
		public StyledPrintStream(PrintStream p) {
			super(p, true);
		}
		public void println(String s) {
			indent(); pgfTag("CellBody");
			pgfString(s);
			endTag();	// end of Para
		}
	}
}
