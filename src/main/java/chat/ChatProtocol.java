package chat;

/** Constants and Class Methods for Java Chat Clients and Server.
 *
 * The protocol:
 *	--> Lusername
 *  --> Btext_to_broadcast
 *	--> Musername\Message
 *	--> Q
 *  <-- any text to be displayed.
 *
 * @author Ian Darwin
 */
public class ChatProtocol {

	// These are the first character of messages from client to server

	public static final int PORTNUM = 9999;
	public static final int MAX_LOGIN_LENGTH = 20;
	public static final char SEPARATOR = '\\';
	public static final char COMMAND = '\\';
	public static final char CMD_LOGIN = 'L';
	public static final char CMD_QUIT  = 'Q';
	public static final char CMD_MESG  = 'M';
	public static final char CMD_BCAST = 'B';

	// These are the first character of messages from server to client

	public static final char RESP_PUBLIC = 'P';
	public static final char RESP_PRIVATE = 'M';
	public static final char RESP_SYSTEM = 'S';

	// TODO in main loop:
	// if (text.charAt(0) == '/')
	//		send(text);
	// else send("B"+text);

	public static boolean isValidLoginName(String login) {
		// check length
		if (login.length() > MAX_LOGIN_LENGTH)
			return false;

		// check for bad chars
		// if (contains bad chars)
		//	return false

		// Passed above tests, is OK
		return true;
	}
}
