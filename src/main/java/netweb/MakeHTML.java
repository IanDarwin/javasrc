import java.awt.*;
import java.applet.*;
import java.net.*;

/*
 * Inspired by this USENET posting:
 * From: pierreba@zorglub.cae.ca (Pierre Baillargeon)
 * Newsgroups: comp.lang.java,comp.lang.java.programmer,comp.lang.java.api
 * Subject: Re: HTML class for Java (applets)?
 * Date: 2 Aug 1996 12:33:31 GMT
 * Organization: CAE Electronics, Montreal
 * Message-ID: <4tssir$a0g@web.cae.ca>                 
 * ...
 * If you want to build your own HTML, I don't think there is a method that
 * will work with all browsers, but this works under Netscape (and maybe under
 * other supporting Javascript): build a javascript URL containing the HTML!
 */

public class MakeHTML extends Applet {
	protected Button goButton;

	public void init() {
		add(goButton = new Button("Go for it!"));
	}

	public boolean action(Event evt, Object o) {
		try {
			URL myNewURL = new URL("JavaScript", "", 0,
				"'<HTML><HEAD><TITLE>Testing!</TITLE></HEAD>" +
				"<BODY><P>Go to" +
				"<A HREF=\"http://www.darwinsys.com\"> " +
				"My Web Page</A>.</BODY></HTML>'" );

			// debug...
			System.out.println("URL = " + myNewURL);

			// "And then a miracle occurs..."
			getAppletContext().showDocument(myNewURL);

		} catch (Exception err) {
			System.err.println("Error!\n" + err);
			showStatus("Error, look in Java Console for details!");
		}
		return true;	// NOTREACHED
	}
}
