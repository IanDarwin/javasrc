import java.applet.*;
import java.awt.*;
import java.awt.event.*;

/**
 * FieldEdit - an Applet to validate data as it's being entered.
 *
 * Not very general: a 52 minute hack to show the mechanics of editing.
 *
 * Does try to leave the cursor in exactly the right position.
 *
 * @author Ian Darwin, http://www.darwinsys.com/
 * @author Bjorn Gudehus, gothic@celestica.com
 */
public class FieldEdit extends Applet {
	/** The label to display the type of thing we're editing */
	private Label myLabel;
	/** The textfield to enter */
	private TextField textField;

	/** Init() is an Applet method used to set up the GUI and listeners */
	public void init() {
		add(myLabel = new Label("Hex:"));
		add(textField = new TextField(10));
		textField.addTextListener(new TextListener() {
			public void textValueChanged(TextEvent ev) {
				int caret = -1;
				TextField tf = FieldEdit.this.textField;
				String s = tf.getText();
				StringBuffer sb = new StringBuffer();
				System.out.println("Text->" + s);
				for (int i=0; i<s.length(); i++) {
					char c = s.charAt(i);
					if (Character.digit(c, 16) >= 0)
						sb.append(c);
					else
						caret = tf.getCaretPosition()-1;
				}
				if (caret >= 0) {
					tf.setText(sb.toString());
					tf.setCaretPosition(caret);
					Toolkit.getDefaultToolkit().beep();
				}
			}
		});
	}
}
