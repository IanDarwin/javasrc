package otherlang;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import org.perl.inline.java.*;

// requires classpath to include this file; usually something like
// .;<perldir>/site/lib/Inline/Java/InlineJavaServer.jar

/** Example Java Class that calls Perl using
 * a non-Java-standard API provided by Perl.
 * <br/>
 * Does not run on its own -- for usage, see StringDistance.pl!
 */
// BEGIN main
public class StringDistance extends InlineJavaPerlCaller {
  JFrame frame;           // visual container
  JTextField tf[], dist;  // text input fields, result output field
  JButton go, exit;       // action buttons

  /* The constructor with possibly 2 initial strings */
  public StringDistance(String[] strs) throws InlineJavaException {
    frame = new JFrame("StringDistance");
    Container p = frame.getContentPane();
    p.setLayout(new GridLayout(0,2));

    // The input fields, including labels:
    tf = new JTextField[2];
    for (int i=0; i<2; i++) {
      p.add(new JLabel("String " + i + ":"));
      tf[i] = new JTextField(20);
      if ((strs != null) && (i < strs.length)) tf[i].setText(strs[i]);
      p.add(tf[i]);
    }

    // The output field, including label:
    p.add(new JLabel("Distance:"));
    dist = new JTextField(5);
    dist.setEditable(false);
    p.add(dist);

    // The main action button:
    go = new JButton("Compute distance");
    go.addActionListener(new ActionListener() {
               public void actionPerformed(ActionEvent ae) {
                 dist.setText(Integer.toString(match(tf[0].getText(),
                                                     tf[1].getText())));
               }
             }
           );
    p.add(go);

    // To finish off:
    exit = new JButton("Exit");
    exit.addActionListener(new ActionListener() {
               public void actionPerformed(ActionEvent ae) {
                 frame.dispose(); System.exit(0);
               }
             }
           );
    p.add(exit);

    if ((strs != null) && (strs.length > 1))
      dist.setText(Integer.toString(match(tf[0].getText(),
                   tf[1].getText())));
    frame.pack();
  }


  // Alternative constructors:
  public StringDistance(String s0, String s1)
      throws InlineJavaException {
    this(new String[] { s0, s1 });
  }

  public StringDistance(String s0) throws InlineJavaException {
    this(new String[] { s0 });
  }

  public StringDistance() throws InlineJavaException {
    this((String[])null);
  }


  /** This shows everything */
  public void show() { frame.setVisible(true); }


  /* Optionally for pre-filling the input fields. */
  public void setText(int fieldno, String str) {
    tf[fieldno].setText(str);
  }


  /** The central interface function to Perl. */
  public int match(String s0, String s1) {
    try {
      String str = (String)CallPerl("Text::Levenshtein", "distance",
                                    new Object [] {s0, s1});
      return Integer.parseInt(str);
    } catch (InlineJavaPerlException e) {
      System.err.println("Inline Java Perl Exception: " + e);
    } catch (InlineJavaException e) {
      System.err.println("Inline Java Exception: " + e);
    }
    return 0;
  }

}
// END main
