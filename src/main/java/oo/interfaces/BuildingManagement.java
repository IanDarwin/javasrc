/**
 * PowerSwitchable is an Interface that will be implemented by things
 * that can safely be turned off at night. We use X10(tm) or similar remote
 * control switching technology to turn things on or off.
 *
 * @author	Ian F. Darwin
 */
interface PowerSwitchable {

	/** The technique for turning this unit off */
	public void powerDown();

	/** The technique for turning this unit on */
	public void powerUp();
}

abstract class TangibleAsset extends Object {
	// No fields or methods; it's just the base of everything.
}

abstract class ComputerComponent extends TangibleAsset {
	// generic computer component stuff here: location, owner, 
	// warranty expiration date :-), etc.
	int deskNumber;
	ComputerComponent(int deskNumber) {
		this.deskNumber = deskNumber;
	}
}
class ComputerCPUCabinet extends ComputerComponent {
	// information about CPU (P5, P6, ...), RAM, etc.
	ComputerCPUCabinet(int deskNumber) {
		super(deskNumber);
	}
}
class ComputerMonitor extends ComputerComponent implements PowerSwitchable {
	ComputerMonitor(int roomNumber) {
		super(roomNumber);
	}
	public void powerDown() {
		System.out.println("Dousing monitor at desk " + deskNumber);
		// send the code to the Monitor's X10 box to shut it off
	}
	public void powerUp() {
		System.out.println("Warming up monitor at desk " + deskNumber);
		// send the code to the Monitor's X10 box to turn it on
	}
}
abstract class BuildingFixture extends TangibleAsset {
	// generic info on things attached to the building
	int room;
	BuildingFixture(int roomNumber) {
		room = roomNumber;
	}
}
abstract class BuildingLight extends BuildingFixture {
	// generic info on lighting: flourescent/incandescent, ...
	BuildingLight(int roomNumber) {
		super(roomNumber);
	}
}
class RoomLights extends BuildingLight implements PowerSwitchable {
	RoomLights(int roomNumber) {
		super(roomNumber);
	}
	public void powerDown() {
		System.out.println("Dousing lights in room " + room);
	}
	public void powerUp() {
		System.out.println("Lighting lights in room " + room);
	}
}
class EmergencyLights extends BuildingLight {	// NEVER SWITCH OFF
	EmergencyLights(int roomNumber) {
		super(roomNumber);
	}
}
/**
 * EnergySaver - control an energy-saving building.
 * This class shows how we might control the objects in an office
 * that can safely be powered off at nighttime to save energy - lots of
 * it, when applied to a large office!
 */
public class EnergySaver {
	// These should be allocated in constructor:
	TangibleAsset things[] = new TangibleAsset[24];	
	int thingNum = 0;

	/** goodNight is called from a timer thread at 2200, or when we
	 * get the "shutdown" command from the security guard.
	 */
	public void goodNight() {
		for (int i=0; i<things.length; i++)
			if (things[i] instanceof PowerSwitchable)
				((PowerSwitchable)things[i]).powerDown();
	}

	// goodMorning() would be the same, but call each one's powerUp().

	/** add is used to add a TangibleAsset to this building */
	public void add(TangibleAsset thing) {	
		System.out.println("Adding " + thing);
		things[thingNum++] = thing;
	}

	public static void main(String av[]) {
		EnergySaver b1 = new EnergySaver();
		b1.add(new RoomLights(101));	// control lights in room 101
		b1.add(new EmergencyLights(101));	// and emerg. lights.
		// add the computer on desk#4 in room 101
		b1.add(new ComputerCPUCabinet(10104));
		// and its monitor
		b1.add(new ComputerMonitor(10104));

		// time passes, and the sun sets...
		b1.goodNight();
	}
}
