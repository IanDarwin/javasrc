package email;

import javax.activation.*;

import java.awt.datatransfer.*;
import java.io.*;

/** Provide a truly minimalist demonstration of using JAF (aka
 * javax.activation, the Java Activation Framework) to read one
 * object and classify it.
 */
public class MimeHandler {

	class PlainHandler implements DataContentHandler {
		private DataFlavor[] taste = {
			new DataFlavor("text/plain", "Plain text"),
			new DataFlavor("text/html", "HTML text")
		};

		public DataFlavor[] getTransferDataFlavors() {
			return taste;
		}

		public Object getTransferData(DataFlavor fl, DataSource ds)
			throws UnsupportedFlavorException, java.io.IOException {
			// XXX
			return null;
		}
		public Object getContent(DataSource ds)
			throws java.io.IOException {
			BufferedReader is =
				new BufferedReader(new InputStreamReader(ds.getInputStream()));
			StringBuffer sb = new StringBuffer();
			int ch;
			while ((ch = is.read()) != -1)
				sb.append((char)ch);
			return sb.toString();
		}
		public void writeTo(Object o, String s, OutputStream os)
			throws java.io.IOException {
			//XXX
			return;
		}
	}

	class MyDataContentHandlerFactory implements DataContentHandlerFactory {
		public DataContentHandler createDataContentHandler(String mimeType){
			if ("text/plain".equals(mimeType)) {
				return new PlainHandler();
			} else if ("text/html".equals(mimeType)) {
				return new PlainHandler();
			} else {
				String mesg = "No handler for " + mimeType;
				throw new IllegalArgumentException(mesg);
			}
		}
	}

	public static void main(String[] args) throws IOException {
		MimeHandler mh = new MimeHandler();

		mh.process(args[0]);
	}

	private void process(String fn) throws IOException {
		DataSource ds = new FileDataSource(fn);

		DataHandler dh = new DataHandler(ds);

		DataHandler.setDataContentHandlerFactory(new MyDataContentHandlerFactory());

		Object data = dh.getContent();

		System.out.println("Content=" + data);
		if (data != null)
			System.out.println("Type   =" + data.getClass().getName());
	}
}
