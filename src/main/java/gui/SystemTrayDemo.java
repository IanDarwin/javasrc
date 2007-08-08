package gui;

import java.awt.AWTException;
import java.awt.Image;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.SystemTray;
import java.awt.Toolkit;
import java.awt.TrayIcon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

/**
 * Simple demo of System Tray.
 * Requires Java 6 or later.
 */
public class SystemTrayDemo {
	static TrayIcon trayIcon;

	public static void main(String[] args) {

		if (!SystemTray.isSupported()) {
			JOptionPane.showMessageDialog(null, "No System tray Support");
			return;
		}

		SystemTray tray = SystemTray.getSystemTray();
		Image image = Toolkit.getDefaultToolkit().getImage("gui/tray.gif");

		PopupMenu popup = new PopupMenu();

		MenuItem wowItem = new MenuItem("Woot Demo");
		popup.add(wowItem);
		wowItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("Woot!!");
			}
		});

		MenuItem quitItem = new MenuItem("Exit Demo");
		quitItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("Exiting...");
				System.exit(0);
			}
		});
		popup.add(quitItem);

		trayIcon = new TrayIcon(image, "Tray Demo", popup);

		ActionListener actionListener = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("Tray actionPerformed");
				trayIcon.displayMessage("Action Event",
						"An Action Event Has Been Performed!",
						TrayIcon.MessageType.INFO);
			}
		};

		trayIcon.setImageAutoSize(true);

		trayIcon.addActionListener(actionListener);

		try {
			tray.add(trayIcon);
		} catch (AWTException e) {
			System.err.println("TrayIcon could not be added.");
		}
	}
}
