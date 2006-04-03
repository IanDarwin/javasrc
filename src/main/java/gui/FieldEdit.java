package gui;

import java.applet.Applet;
import java.awt.Label;
import java.awt.TextField;

/**
 * FieldEdit - an Applet to validate data as it's being entered.
 * 
 * Not very general: a quick hack to show the mechanics of editing.
 * 
 * Does try to leave the cursor in exactly the right position.
 * 
 * @author Ian Darwin, http://www.darwinsys.com/
 * @author Bjorn Gudehus, gothic@celestica.com
 */
public class FieldEdit extends Applet {

	/** The textfield to enter */
	private TextField textField;

	/** Init() is an Applet method used to set up the GUI and listeners */
	public void init() {
		add(new Label("Hex:"));
		add(textField = new TextField(10));
		textField.addTextListener(new FieldEditingListener(textField));
	};

}
