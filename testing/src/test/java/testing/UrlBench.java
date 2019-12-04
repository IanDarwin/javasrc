package testing;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

/**
 * Repeatedly bang on a URL. Written to answer this query:
 * > How would one write or script a utility that would click on specified
 * > buttons in a form, and force a submit? I need to hammer a script to see how
 * > it handles, and 'click-submit-back-click-submit-back' ad infinitum doesn't 
 * > cut it.
 * Actually, Apache JMeter will do this without writing any code...
 */

public class UrlBench {
        /** The test target must be entered here as a full URL
         * and can include any number of parameters in the
         * usual form.
         */
        public static final String TEST_TARGET =
                //"http://www.foo.bar/cgi-bin/whatIwannaTest?name=Ian&value=high";
                "http://127.0.0.1:8080/CalendarPage.jsp";
        public static void main(String[] args) throws IOException {
                for (int i = 1; i<100000; i++) {
                        URL u= new URL(TEST_TARGET);
                        BufferedReader is = new BufferedReader(
                                new InputStreamReader(u.openStream()));
                        String line;
                        while ((line = is.readLine()) != null) {
                                // System.out.println(line);
                        }
                }
        }
}
