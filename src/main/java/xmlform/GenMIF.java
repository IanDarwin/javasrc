package xmlform;

import io.GetMark;

import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;
import java.io.PrintStream;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import org.w3c.dom.CharacterData;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * Class with code to walk a tree and convert it to Maker Interchange
 * Format (MIF). Must make MIF (not MML) since, alas, MML loses named
 * character codes in input.
 * <P>
 * Along the way, we do some book-specific things, like running
 * another Java class and grabbing the output back into here.
 *
 * @author Ian F. Darwin, http://www.darwinsys.com/
 */
// BEGIN main
public class GenMIF implements XmlFormWalker {
	/** The normal output writer */
	protected PrintStream msg;
	/** Specialized PrintStream for use by GetMark. */
	protected StyledPrintStream smsg;
	/** A GetMark converter for source code. */
	protected GetMark gm = new GetMark();
	/** Vector used to print indented lines */
	protected List<String> indents;
	/** The Document */
	Document theDocument;

	/** Construct a converter object */
	GenMIF(Document doc, PrintStream pw) {
		theDocument = doc;
		msg = new PrintStream(pw);
		smsg = new StyledPrintStream(msg);
		// Reassign System.out to go there as well, so when we
		// run other main classes, their output gets grabbed.
		System.setOut(smsg);
		indents = new ArrayList<>();
		indents.add("");
	}

	protected int indent = 0;
	protected void indent() {
		if (indent > indents.size()) {
			StringBuffer sb = new StringBuffer();
			for (int i=0; i<indent; i++) {
				sb.append(' ');
				sb.append(' ');
			}
			indents.add(sb.toString());
		}
		msg.print(indents.get(indent>0?indent-1:0));
	}

	protected Stack<String> tagStack = new Stack<>();
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

		doRecursive(theDocument);		// start recursing the document
	}

	protected void doRecursive(Node n) {
		NodeList kids;
		if (n == null)
			return;

		doNode(n);

		kids = n.getChildNodes();
		int nkids = kids.getLength();
		for (int i=0; i<nkids; i++) {
			doRecursive(kids.item(i));
		}
	}

	protected void doNode(Node p) {
		if (p.getNodeType() == Node.ELEMENT_NODE)
			doElement((Element)p);
		else if (p.getNodeType() == Node.TEXT_NODE)
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
		if (tag.equals("head")) {
			System.err.println(">>>>Start HEAD");
		} else if (tag.equals("body")) {
			System.err.println(">>>>Start BODY");
		} else if (tag.equals("chapter")) {
			doChapter(p);
		//
		// PARAGRAPH TAGS
		// This is the application-specific bit of code.
		// SHOULD BE MAP RULES, NOT HARD-CODED.
		//
		} else if (tag.equals("title")) {
			doParagraph("ChapterTitle", p);
		} else if (tag.equals("simplesect")) {
			doParagraph("HeadA", p);
		} else if (tag.equals("sect1")) {
			doParagraph("HeadA", p);
		} else if (tag.equals("para")) {
			doParagraph("Body", p);
		} else if (tag.equals("sect2")) {
			doParagraph("HeadB", p);
		} else if (tag.equals("programlisting")) {
			doPre(p);
		} else if (tag.equals("graphic")) {
			doPre(p);
		} else if (tag.equals("runoutput")) {
			doRun(p);
		} else if (tag.equals("figure")) {
			doPre(p);
		//
		// STYLE TAGS
		//
		} else if (tag.equals("kb")) {	// keyboard, map to code
			System.err.println("<KB> handler not written yet");
		} else if (tag.equals("bt")) {	// book title, map to Citation
			System.err.println("<BT> handler not written yet");
		} else
			System.err.println("IGNORING UNHANDLED TAG " + tag + '(' +
				p.getClass() + '@' + p.hashCode() + ')');
	}

	protected void doChapter(Element p) {
		msg.println("# START OF CHAPTER");
		makeUpParagraph("ChapterStart", null);
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

	/** Synthesize a paragraph when we know its content.
	 * content can be null for things like Label paragraphs.
	 */
	protected void makeUpParagraph(String tag, String contents) {
		indent(); pgfTag(tag);
		if (contents != null)
			pgfString(contents);
		endTag();
	}

	/** EXAMPLEs are longer than CODEs, and are not limited by //+ //-
	 * marks, which are therefore not required.
	 * XXX TODO wrap a TABLE around the output.
	 */
	protected void doExample(Element p) {
		NamedNodeMap attrs = p.getAttributes();
		Node href;
		if ((href = attrs.getNamedItem("HREF")) == null)
			throw new IllegalArgumentException(
				"node " + p + "lacks required HREF Attribute");
		String fname = href.getNodeValue();
		System.err.println("Making an EXAMPLE out of " + fname);
	
		makeUpParagraph("ExampleLabel", null);
		makeUpParagraph("ExampleTitle", fname);

		String lfname = System.getProperty("codedir", ".") + '/' + fname;	
		try (LineNumberReader is = new LineNumberReader(new FileReader(lfname))) {
			String line;
			while ((line = is.readLine()) != null) {
				indent(); pgfTag("Code");
				pgfString(line);
				endTag();	// end of Para
			}
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
				"node " + p + "lacks required CLASS Attribute");
		String className = myClass.getNodeValue();

		// makeUpParagraph("Example", "Example XX: " + className);

		try {
			// First, find the class.
			Class<?> c = Class.forName(className);

			// Create a dummy argv to pass it.
			String[] argv = new String[0];

			// Create the array of Argument Types
			Class<?>[] argTypes = {
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

	/** Code is inserted, but only between / / + and / / - tags */
	protected void doCode(Element p) {
		NamedNodeMap attrs = p.getAttributes();
		Node href;
		if ((href = attrs.getNamedItem("HREF")) == null)
			throw new IllegalArgumentException(
				"node " + p + "lacks required HREF Attribute");
		String fname = href.getNodeValue();
		System.err.println("En-CODE-ing " + fname);

		makeUpParagraph("Code", "// " + fname);
	
		try {
			fname = System.getProperty("codedir", ".") + '/' + fname;	
			LineNumberReader is = new LineNumberReader(new FileReader(fname));
			gm.process(fname, is, smsg);
		} catch(IOException e) {
			throw new IllegalArgumentException(e.toString());
		}
	}

	protected void doPre(Element p) {
		doParagraph("Code", p);
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
			// System.err.println("NODE " + n.getNodeType());
			switch(n.getNodeType()) {
				case Node.TEXT_NODE:
					// System.err.println("\tCDATA: " + n.getNodeValue());
					doCData((CharacterData)n);
					p.removeChild(n);
					break;
				case Node.ELEMENT_NODE:
					// System.err.println("\tELEMENT<" + n.getNodeName() + ">");
					doChildren((Element)n);
					p.removeChild(n);
					break;
				default:
					System.err.println( "Warning: unhandled child node " +
						n.getNodeType() + ": " + n.getClass());
					break;
			}
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
			case '\t':	b.append("\\t"); break;
			case '\'':	b.append("\\xd5 "); break;
			case '<':	b.append("\\<"); break;
			case '>':	b.append("\\>"); break;
			case '\r': case '\n': b.append(' '); break;
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
			indent(); pgfTag("Code");
			pgfString(s);
			endTag();	// end of Para
		}
	}
}
// END main
