package plotter;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import javax.comm.CommPortIdentifier;
import javax.comm.NoSuchPortException;
import javax.comm.PortInUseException;
import javax.comm.SerialPort;
import javax.comm.UnsupportedCommOperationException;

/**
 * A Plotter subclass for drawing on a Penman plotter.
 * These were made in the UK and sold into North American markets.
 * It is a little "turtle" style robot plotter that communicates
 * over a serial port. For this, we use the "Java Communicatons" API.
 * Java Communications is a "standard extention" and must be downloaded
 * and installed separately from the JDK before you can even compile this 
 * program.
 *
 * @author	Ian Darwin, http://www.darwinsys.com/
 */
public class Penman extends Plotter {
	private final String OK_PROMPT = "\r\n!";
	private final int MAX_REPLY_BYTES = 50;	// paranoid upper bound
	private SerialPort tty;
	private DataInputStream is;
	private DataOutputStream os;

	/** Construct a Penman plotter object */
	public Penman() throws NoSuchPortException,PortInUseException,
			IOException,UnsupportedCommOperationException {
		super();
		init_comm("COM2");		// setup serial commx
		init_plotter();		// set plotter to good state
	}

	private void init_plotter() {
		send("I"); expect('!');	// eat VERSION etc., up to !
		send("I"); expect('!');	// wait for it!
		send("H");		// find home position
		expect('!');	// wait for it!
		send("A");		// Set to use absolute coordinates
		expect('!');
		curx = cury = 0;
		penUp();
	}

	//
	// PUBLIC DRAWING ROUTINES
	//

	public void setFont(String fName, int fSize) {
		// Font name is ignored for now...

		// Penman's size is in mm, fsize in points (inch/72).
		int size = (int)(fSize*25.4f/72);
		send("S"+size + ","); expect(OK_PROMPT);
		System.err.println("Font set request: " + fName + "/" + fSize);
	}

	public void drawString(String mesg) {
		send("L" + mesg + "\r"); expect(OK_PROMPT);
	}

	/** Move to a relative location */
	public void rmoveTo(int incrx, int incry){
		moveTo(curx + incrx, cury + incry);
	}

	/** move to absolute location */
	public void moveTo(int absx, int absy) {
		System.err.println("moveTo ["+absx+","+absy+"]");
		curx = absx;
		cury = absy;
		send("M" + curx + "," + cury + ","); expect(OK_PROMPT);
	}

	private void setPenState(boolean up) {
		penIsUp = up;
		System.err.println("Pen Up is ["+penIsUp+"]");
	}

	public void penUp() {
		setPenState(true);
		send("U"); expect(OK_PROMPT);
	}
	public void penDown() {
		setPenState(false);
		send("D"); expect(OK_PROMPT);
	}
	public void penColor(int c) {
		penColor = (c%3)+1;		// only has 3 pens, 4->1
		System.err.println("PenColor is ["+penColor+"]");
		send("P" + c + ","); expect(OK_PROMPT);
	}

	//
	// PRIVATE COMMUNICATION ROUTINES
	//

	/** Set up communication. 
	 * <br/>
	 * XXX: Should probably re-use CommPortOpen instead.
	 */
	private void init_comm(String portName) throws NoSuchPortException,PortInUseException,
			IOException,UnsupportedCommOperationException {

		// get list of ports available on this particular computer.
		// Enumeration pList = CommPortIdentifier.getPortIdentifiers();

		// Print the list. A GUI program would put these in a chooser!
		// while (pList.hasMoreElements()) {
			// CommPortIdentifier cpi = (CommPortIdentifier)pList.nextElement();
			// System.err.println("Port " + cpi.getName());
		// }
		
		// Open a port. 
		CommPortIdentifier port =
			CommPortIdentifier.getPortIdentifier(portName);

		// This form of open takes an Application Name and a timeout.
		tty = (SerialPort) port.open("Penman Driver", 1000);

		// set up the serial port
		tty.setSerialPortParams(9600, SerialPort.DATABITS_8,
			SerialPort.STOPBITS_1, SerialPort.PARITY_NONE);
		tty.setFlowControlMode(SerialPort.FLOWCONTROL_RTSCTS_OUT|
			SerialPort.FLOWCONTROL_RTSCTS_OUT);

		// Get the input and output streams
		is = new DataInputStream(tty.getInputStream());
		os = new DataOutputStream(tty.getOutputStream());
	}

	/** Send a command to the plotter. Although the argument is a String,
	 * we send each char as a *byte*, so avoid 16-bit characters!
	 * Not that it matters: the Penman only knows about 8-bit chars.
	 */
	private	void send(String s) {
		System.err.println("sending " + s + "...");
		try {
			for (int i=0; i<s.length(); i++)
				os.writeByte(s.charAt(i));
		} catch(IOException e) {
			e.printStackTrace();
		}
	}

	/** Expect a given CHAR for a result */
	private	void expect(char s) {
		byte b;
		try {
			for (int i=0; i<MAX_REPLY_BYTES; i++){
				if ((b = is.readByte()) == s) {
						return;
				}
				System.err.print((char)b);
			}
		} catch (IOException e) {
			System.err.println("Penman:expect(char "+s+"): Read failed");
			System.exit(1);
		}
		System.err.println("ARGHH!");
	}

	/** Expect a given String for a result */
	private	void expect(String s) {
		byte ans[] = new byte[s.length()];

		System.err.println("expect " + s + " ...");
		try {
			is.read(ans);
		} catch (IOException e) {
			System.err.println("Penman:expect(String "+s+"): Read failed");
			System.exit(1);
		};
		for (int i=0; i<s.length() && i<ans.length; i++)
			if (ans[i] != s.charAt(i)) {
				System.err.println("MISMATCH");
				break;
			}
		System.err.println("GOT: " + new String(ans));

	}
}
