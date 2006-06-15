package printjdk14printservice;

import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;

import javax.print.Doc;
import javax.print.DocFlavor;
import javax.print.attribute.DocAttributeSet;

public final class MyDemoDoc implements Doc {
	
	private DocFlavor flavor;
	private String fileName;

	public MyDemoDoc(String fileName, DocFlavor flavor) {
		this.fileName = fileName;
		this.flavor = flavor;
	}

	public DocFlavor getDocFlavor() {
		return flavor;
	}

	public Object getPrintData() throws IOException {
		return null;
	}

	public DocAttributeSet getAttributes() {
		return null;
	}

	public Reader getReaderForText() throws IOException {
		return null;
	}

	public InputStream getStreamForBytes() throws IOException {
		return getClass().getResourceAsStream(fileName);
	}

}
