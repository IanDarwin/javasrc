package gui;

import java.awt.SplashScreen;

import javax.swing.JOptionPane;

public class SplashDemo {
	public static void main(String[] args) {
		final SplashScreen splashScreen = SplashScreen.getSplashScreen();
		if (splashScreen == null) {
			JOptionPane.showMessageDialog(null, "Could not get Splash Screen");
			return;
		}
		System.out.println(splashScreen);
	}
}
