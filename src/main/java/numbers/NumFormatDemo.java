import java.text.*;
import java.util.*;

/*
 * Program to show examples of NumberFormat at work.
 * Adapted from the NumberFormat javadoc page.
 */
public class NumFormatTest {
   /** The main (and only) method in this class. */
   public static void main(String av[]) { 
     /** The list of all Locales that NumberFormat has.
      * Normally we would have a GUI with a menu for this
      */
     Locale[] locales = NumberFormat.getAvailableLocales();
     /** The number we print in many formats */
     double myNumber = -1234.56;
     NumberFormat form;
     // just for fun, we print out a number with the locale number, currency
     // and percent format for each locale we can.
     for (int j = 0; j < 3; ++j) {
         System.out.println("FORMAT GROUP " + j);
         for (int i = 0; i < locales.length; ++i) {
             if (locales[i].getCountry().length() == 0) {
                // skip language-only
                continue;
             }
             System.out.print(locales[i].getDisplayName());
             switch (j) {
             default:
                 form = NumberFormat.getInstance(locales[i]); break;
             case 1:
                 form = NumberFormat.getCurrencyInstance(locales[i]); break;
             case 0:
                 form = NumberFormat.getPercentInstance(locales[i]); break;
             }
             try {
		 // toPattern() will reveal the combination of #0., etc
		 // that this particular local uses to format with!
                 System.out.print(": " + ((DecimalFormat)form).toPattern()
                              + " -> " + form.format(myNumber));
             } catch (IllegalArgumentException iae) { }
             try {
                 System.out.println(" -> " + form.parse(form.format(myNumber)));
             } catch (ParseException pe) { }
         }
       }
    }
}
