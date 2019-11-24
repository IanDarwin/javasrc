package oo.interfaces;

/**
 * PowerSwitchable is an Interface that will be implemented by things
 * that can safely be turned off at night. Could use X10(tm) or BlueTooth
 * or 802.11 or any similar network technology to turn things on or off.
 *
 * @author	Ian F. Darwin
 */
public interface PowerSwitchable {

	/** The technique for turning this unit off */
	public void powerDown();

	/** The technique for turning this unit on */
	public void powerUp();
}
