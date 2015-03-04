package applet.linksapplet;

import java.awt.*;
import java.awt.event.*;
import java.applet.*;
import java.util.*;
import java.net.*;

/** Clone of the "link" applet from 
 * http://www.htmlgoodies.com/beyond/popupjava.html
 * It had to be cloned because he doesn't give his Java source code out,
 * and some people need to see that.
 * (His "source code" is really the HTML code needed to make it work,
 * and is no more "source code" than a book about driving is a new car.
 *
 * @author  Ian F. Darwin, http://www.darwinsys.com/
 */
public class Link extends Applet implements ItemListener {

	private static final long serialVersionUID = 1L;
	Choice theChoice;
	Map<String,String> theMap;

	/** The init method is used to initialize the applet. */
	public void init() {
		theChoice = new Choice();
		add(theChoice);
		theChoice.addItemListener(this);
		theMap = new Hashtable<>();
		String nOptions = getParameter("number");
		int n;
		if (nOptions == null)
			n = 0;
		else n = Integer.parseInt(nOptions);
		for (int i=0; i<n; i++) {
			String link = getParameter("link" + i);
			// System.out.println(i + ": " + link);
			int bs = link.indexOf("\\");
			String theTitle = link.substring(0, bs);
			String theURL = link.substring(bs+1);
			// System.out.println(theTitle + "-->" + theURL);
			theChoice.add(theTitle);
			theMap.put(theTitle, theURL);
		}
	}

	/** This method is called by the Choice whenever a choice
	 * is selected.
	 */
	public void itemStateChanged(ItemEvent e) {
		URL whereURLwantToGo = null;
		String whereYouWantToGo = (String)theMap.get(theChoice.getSelectedItem());
		try { 
			whereURLwantToGo = new URL(whereYouWantToGo);
		} catch (MalformedURLException malheur) {
			showStatus("BAD URL: see Java Console");
			System.err.println("Bad URL: " + malheur);
		}
		getAppletContext().showDocument(whereURLwantToGo);
	}
}
