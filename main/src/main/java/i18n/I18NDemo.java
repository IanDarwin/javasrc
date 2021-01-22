package i18n;

import java.util.ResourceBundle;

import javax.swing.JButton;

import com.darwinsys.swingui.I18N;

/** Quickie Demo showing use of I18N */
public class I18NDemo {
	public static void main(String[] args) {
		ResourceBundle menuBundle = ResourceBundle.getBundle("Widgets");
		JButton jb = I18N.getButton(menuBundle, "exit.label", "Quit");
		System.out.printf("Button text = %s, button = %s\n",
			jb.getText(), jb);
	}
}
