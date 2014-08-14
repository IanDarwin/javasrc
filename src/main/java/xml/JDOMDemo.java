package xml;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import org.jdom.Comment;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.ProcessingInstruction;
import org.jdom.input.SAXBuilder;
import org.jdom.output.DOMOutputter;

/*
 * Simple demo of JDOM
 */
public class JDOMDemo {

	public static void main(String[] args) {

		// Must be at least one file or URL argument
		String fileName = null;
		try {
			if (args.length == 0) {
				fileName = "build.xml";
				process(fileName);
			} else {
				for (String arg : args) {
					fileName = arg;
					process(fileName);
				}
			}
		} catch (JDOMException e) { //  well-formedness or other error
			System.out.println(fileName + " is not a well formed XML document.");
			System.out.println(e.getMessage());
		} catch (IOException ex) {
			System.out.println("Input or Output error:" + fileName + ": " + ex);
		}

	}

	/** Show writing out DOM document.
	 * @param jdomDocument
	 * @param domOutputter
	 * @throws JDOMException
	 * @throws IOException 
	 */
	private static void process(String fileName) throws JDOMException, IOException {
		System.out.println("JDOMDemo.process(): starting file " + fileName);
		
		SAXBuilder saxBuilder = new SAXBuilder();
		
		Document jdomDocument = saxBuilder.build(fileName);
		
		DOMOutputter domOutputter = new DOMOutputter();
		
		domOutputter.output(jdomDocument);
	}

	/**
	 * Show iterating over a Document and printing some elements.
	 * @param doc
	 */
	public static void demo(Document doc) {

		for (Object o : doc.getContent()) {
			if (o instanceof Element) {
				demo((Element) o);
			} else if (o instanceof Comment)
				doComment((Comment) o);
			else if (o instanceof ProcessingInstruction)
				doPI((ProcessingInstruction) o);
		}
	}

	/** Show iterating over one Element and its kids.
	 * @param element
	 */
	public static void demo(Element element) {
		System.out.println("Element " + element);

		List<?> children = element.getContent();
		Iterator<?> iterator = children.iterator();
		while (iterator.hasNext()) {
			Object o = iterator.next();
			if (o instanceof Element) {
				demo((Element) o);
			} else if (o instanceof Comment)
				doComment((Comment) o);
			else if (o instanceof ProcessingInstruction)
				doPI((ProcessingInstruction) o);
			else if (o instanceof String) {
				System.out.println("String: " + o);
			}
		}
	}

	public static void doComment(Comment c) {
		System.out.println("Comment: " + c);
	}

	public static void doPI(ProcessingInstruction pi) {
		System.out.println("PI: " + pi);
	}
}
