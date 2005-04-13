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
				System.out.println("JDOMDemo.main(): using " + fileName);
	
				process(fileName);
			} else {
				for (int i = 0; i < args.length; i++) {
					System.out.println("JDOMDemo.main(): using " + (fileName = args[i]));

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
		System.out.println("JDOMDemo.myWriteOut()");
		
		SAXBuilder saxBuilder = new SAXBuilder();
		
		Document jdomDocument = saxBuilder.build(fileName);
		
		DOMOutputter domOutputter = new DOMOutputter();
		
		org.w3c.dom.Document domElement = domOutputter.output(jdomDocument);
	}

	/**
	 * Show iterating over a Document and printing some elements.
	 * @param doc
	 */
	public static void demo(Document doc) {

		List children = doc.getContent();
		Iterator iterator = children.iterator();
		while (iterator.hasNext()) {
			Object o = iterator.next();
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

		List attributes = element.getAttributes();
		List children = element.getContent();
		Iterator iterator = children.iterator();
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
