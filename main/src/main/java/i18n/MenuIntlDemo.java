package i18n;

import javax.swing.*;
import java.util.ResourceBundle;

/** Quickie Demo showing use of MenuIntl */
public class MenuIntlDemo {
	public static void main(String[] args) {
		ResourceBundle menuBundle = new ResourceBundle.getBundle("Menus");
		JButton jb = MenuIntl.getButton("exit.label", "Exit");
		System.out.printf("Button text = %s, button = %s\n",
			jb.getText(), jb);
	}
}
