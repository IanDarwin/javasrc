package oo.interfaces;

/**
 * BuildingManagement - control an energy-saving building.
 * This class shows how we might control the objects in an office
 * that can safely be powered off at nighttime to save energy - lots of
 * it, when applied to a large office!
 */
// BEGIN main
public class BuildingManagement {

	Asset things[] = new Asset[24];
	int numItems = 0;

	/** Scenario: goodNight() is called from a timer Thread at 2200, or when
	 * we get the "shutdown" command from the security guard.
	 */
	public void goodNight() {
		for (int i=0; i<things.length; i++)
			if (things[i] instanceof PowerSwitchable)
				((PowerSwitchable)things[i]).powerDown();
	}

	// goodMorning() would be the same, but call each one's powerUp().

	/** Add a Asset to this building */
	public void add(Asset thing) {
		System.out.println("Adding " + thing);
		things[numItems++] = thing;
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
// END main
