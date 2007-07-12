package gui;

import java.awt.AWTException;
import java.awt.Image;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.SystemTray;
import java.awt.TrayIcon;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

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
		Image image = Toolkit.getDefaultToolkit().getImage("tray.gif");

		MouseListener mouseListener = new MouseListener() {

			public void mouseClicked(MouseEvent e) {
				System.out.println("Tray Icon - Mouse clicked!");
			}

			public void mouseEntered(MouseEvent e) {
				System.out.println("Tray Icon - Mouse entered!");
			}

			public void mouseExited(MouseEvent e) {
				System.out.println("Tray Icon - Mouse exited!");
			}

			public void mousePressed(MouseEvent e) {
				System.out.println("Tray Icon - Mouse pressed!");
			}

			public void mouseReleased(MouseEvent e) {
				System.out.println("Tray Icon - Mouse released!");
			}
		};

		ActionListener exitListener = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("Exiting...");
				System.exit(0);
			}
		};

		PopupMenu popup = new PopupMenu();
		MenuItem defaultItem = new MenuItem("Exit Demo");
		defaultItem.addActionListener(exitListener);
		popup.add(defaultItem);
		MenuItem wowItem = new MenuItem("Exit Demo");
		popup.add(wowItem);

		trayIcon = new TrayIcon(image, "Tray Demo", popup);

		ActionListener actionListener = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				trayIcon.displayMessage("Action Event",
						"An Action Event Has Been Performed!",
						TrayIcon.MessageType.INFO);
			}
		};

		trayIcon.setImageAutoSize(true);
		trayIcon.addActionListener(actionListener);
		trayIcon.addMouseListener(mouseListener);

		try {
			tray.add(trayIcon);
		} catch (AWTException e) {
			System.err.println("TrayIcon could not be added.");
		}

	}

}
