package io.lookup;

import java.applet.Applet;
import java.awt.Button;
import java.awt.Label;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;

public class ClassDoc extends Applet {

	private static final long serialVersionUID = 1L;
	/** The default place to find API doc. */
	protected final static String DEFAULT_PREFIX =
		"file:///C|/jdk1.2/docs/api/";
	/** The place to find API doc. */
	protected String prefix;
	/** The page for conflicts and unknown classes */
	protected final static String DEFAULT_PAGE = "index-files/index-1";
	/** The classname entry textfield. */
	TextField tf = null;
	/** The mapping from short to long names */
	protected Properties map;
	URL listURL = null;

	/** Initialize the Applet */
	public void init() {
		tf = new TextField(10);
		add(new Label("Class: "));
		add(tf);
		ActionListener handler = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String name = tf.getText();
				if (name.length() == 0)
					return;
				try {
					getAppletContext().showDocument(
						new URL(lookup(name)));
				} catch (MalformedURLException exc) {
					System.err.println(exc);
					showStatus("ERROR See Java Console");
				}
			}
		};
		tf.addActionListener(handler);
		Button go = new Button("Class Document");
		go.addActionListener(handler);
		add(go);

		Button b = new Button("List All");
		add(b);
		b.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (listURL != null)
					getAppletContext().showDocument(listURL);
			}
		});
		prefix = getParameter("prefix");
		if (prefix == null)
			prefix = DEFAULT_PREFIX;
		if (!prefix.endsWith("/"))
			prefix = prefix + "/";

		map = new Properties();
		try {
			listURL = new URL(getCodeBase(),
				"ClassDoc.properties");
			map.load(listURL.openStream());
		} catch (Exception exc) {
			System.err.println(exc);
			showStatus("ERROR See Java Console");
		}
	}

	/** Start the Applet - called when the page is ready, or when
	 * we come back to this page. Select All text, so the user
	 * can overtype it if they want - this is the most common case.
	 */
	public void start() {
		tf.selectAll();
	}

	final static String HTML = ".html";

	protected String lookup(String txt) {
		String name = map.getProperty(txt.toLowerCase());
		System.out.println("lookup("+txt+"-->"+name);
		if (name != null) {
			return prefix + name.replace('.','/') + HTML;
		}
		return prefix + DEFAULT_PAGE + HTML;
	}
}
