package javadoc;

import java.awt.Button;
import java.awt.Color;
import java.awt.Graphics;
import java.time.DayOfWeek;
import java.time.LocalDate;

import javax.swing.JPanel;

// tag::main[]
/**
 * JavadocDemo - a simple example to show JavaDoc comments.
 * @author Ian F. Darwin, https://darwinsys.com/
 */
public class JavadocDemo extends JPanel {

  private static final long serialVersionUID = 1L;

  /**
   * Construct the GUI
   * @throws java.lang.IllegalArgumentException if constructed on a Sunday.
   */
  public JavadocDemo() {
    // We create and add a pushbutton here, 
    // but it doesn't do anything yet.
    Button b = new Button("Hello");
    add(b);            // connect Button into component
    // Totally capricious example of what you should not do
    if (LocalDate.now().getDayOfWeek() == DayOfWeek.SUNDAY) {
      throw new IllegalArgumentException("Never On A Sunday");
    }
  }

  /** paint() is an AWT Component method, called when the 
   *  component needs to be painted. This one just draws colored
   * boxes in the window.
   *
   * @param g A java.awt.Graphics that we use for all our
   * drawing methods.
   */
  @Override
  public void paint(Graphics g) {
    // ...
    // end::main[]
    int w = getSize().width, h = getSize().height;
    g.setColor(Color.YELLOW);
    g.fillRect(0, 0, w/2, h);
    g.setColor(Color.GREEN);
    g.fillRect(w/2, 0, w, h);
    g.setColor(Color.BLACK);
    g.drawString("Welcome to Java", 50, 50);
  }

  // tag::snippet-int[]
  /**
   * A simple demo method. Typical usage:
   * {@snippet lang="java" :
   *  var demo = new JavadocDemo();
   *  demo.demo(42); // or some other int
   * }
   * @param i The value to be processed.
   */
  public void demo(int i) {
    System.out.printf("Demo value is %d\n", i);
  }
  // end::snippet-int[]

  // tag::snippet-ext[]
  /**
   * A simple method. See this note:
   * {@snippet lang="python" file="snyde_comment.py"}
   * @param i The value to be processed.
   */
  public void demo2(int i) {
    System.out.printf("Demo value is %d\n", i);
  }
  // end::snippet-ext[]
}
