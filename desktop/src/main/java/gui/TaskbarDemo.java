package gui;

import java.awt.*;

// tag::main[]
/**
 * Explorations with the Java 9+ AWT "Taskbar" interface.
 */
public class TaskbarDemo {
	public static void main(String[] args) throws Exception {
		if (Taskbar.isTaskbarSupported()) {
			Taskbar t = Taskbar.getTaskbar();
			if (t.isSupported(Taskbar.Feature.ICON_BADGE_TEXT)) {
				t.setIconBadge("Howdy!");
			} else {
				System.out.println("Badge not supported");
			}
		} else {
			System.out.println("Taskbar not supported");
		}
		System.out.println("Now for some shut-eye.");
		Thread.sleep(5 * 1000);
	}
}
// end::main[]
