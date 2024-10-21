package oo.interfaces;

import java.util.ArrayList;
import java.util.List;

/**
 * BuildingManagement - control an energy-saving building.
 * This class shows how we might control the objects in an office
 * that can safely be powered off at nighttime to save energy - lots of
 * it, when applied to a large office!
 */
// tag::main[]
public class BuildingManagement {

	List<Asset> things = new ArrayList<>();

	/** Scenario: goodNight() is called from a timer Thread at 2200, or when
	 * we get the "shutdown" command from the security guard.
	 */
	public void goodNight() {
		things.forEach(obj -> {
			if (obj instanceof PowerSwitchable switchable) // Java 14 way
				switchable.powerDown();
			});
	}

	// tag::functional[]
	public void goodNightFunctional() {
		things.stream().filter(obj -> obj instanceof PowerSwitchable)
			.forEach(obj -> ((PowerSwitchable)obj).powerDown());
	}
	// end::functional[]

	// goodMorning() would be similar, but call each one's powerUp().

	/** Add a Asset to this building */
	public void add(Asset thing) {
		System.out.println("Adding " + thing);
		things.add(thing);
	}

	/** The main program */
	public static void main(String[] av) {
		BuildingManagement b1 = new BuildingManagement();
		b1.add(new RoomLights(101));	// control lights in room 101
		b1.add(new EmergencyLight(101));	// and emerg. lights.
		// add the computer on desk#4 in room 101
		b1.add(new ComputerCPU(10104));
		// and its monitor
		b1.add(new ComputerMonitor(10104));

		// time passes, and the sun sets...
		b1.goodNight();
	}
}
// end::main[]
