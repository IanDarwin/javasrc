package email;

/** Simply a list of names for the Mail System to use.
 * If you "implement" this interface, you don't have to prefix
 * all the names with MailProps in your code.
 */
public interface MailConstants {
	public static final String PROPS_FILE_NAME = "MailClient.properties";

	public static final String SEND_PROTO = "Mail.send.protocol";
	public static final String SEND_USER  = "Mail.send.user";
	public static final String SEND_PASS  = "Mail.send.password";
	public static final String SEND_ROOT  = "Mail.send.root";
	public static final String SEND_HOST  = "Mail.send.host";
	public static final String SEND_DEBUG = "Mail.send.debug";

	public static final String RECV_PROTO = "Mail.receive.protocol";
	public static final String RECV_PORT  = "Mail.receive.port";
	public static final String RECV_USER  = "Mail.receive.user";
	public static final String RECV_PASS  = "Mail.receive.password";
	public static final String RECV_ROOT  = "Mail.receive.root";
	public static final String RECV_HOST  = "Mail.receive.host";
	public static final String RECV_DEBUG = "Mail.receive.debug";
}
