/*
 * Simple demo of JDom
 */

import org.jdom.*;
import org.jdom.input.SAXBuilder;
import org.jdom.input.DOMBuilder;
import org.jdom.output.*;
import java.util.*;


public class JDomDemo {

    public static void main(String[] args) {
  
		// Must be at least one file or URL argument
        if (args.length == 0) {
            System.out.println("Usage: java JDomDemo URL [...]"); 
        } 
        
        SAXBuilder saxBuilder = new SAXBuilder();
        DOMBuilder domBuilder = new DOMBuilder();
                
        for (int i = 0; i < args.length; i++) {
      
            try {
                Document jdomDocument = saxBuilder.build(args[i]);

                DOMOutputter domOutputter = new DOMOutputter();

                /*
                 * Test getting DOM Document from JDOM Document
                org.w3c.dom.Document domDocument = domOutputter.output(doc);
                 */

                /*
                 * Test getting DOM Element from JDOM Element
                 */
                org.w3c.dom.Element domElement = domOutputter.output(jdomDocument.getRootElement());

                /*
                 * Test getting JDOM Element from DOM Element
                 */
                org.jdom.Element jdomElement = domBuilder.build(domElement);
                demo(jdomElement);

            } catch (JDOMException e) { // indicates a well-formedness or other error
                System.out.println(args[i] + " is not a well formed XML document.");
                System.out.println(e.getMessage());
            }      
        }
    }  

    public static void demo(Document doc) {

        List children = doc.getMixedContent();
        Iterator iterator = children.iterator();
        while (iterator.hasNext()) {
            Object o = iterator.next();
            if (o instanceof Element) {
                demo((Element) o);
            }
            else if (o instanceof Comment)
				doComment((Comment) o);
            else if (o instanceof ProcessingInstruction) 
				doPI((ProcessingInstruction) o);
        }
    }     

    public static void demo(Element element) {
		System.out.println("Element " + element);

        List attributes = element.getAttributes();
        List children = element.getMixedContent();
        Iterator iterator = children.iterator();
        while (iterator.hasNext()) {
            Object o = iterator.next();
            if (o instanceof Element) {
                demo((Element) o);
            }
            else if (o instanceof Comment) 
				doComment((Comment)o);
            else if (o instanceof ProcessingInstruction) 
				doPI((ProcessingInstruction)o);
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
