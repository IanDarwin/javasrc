package email;

import jakarta.mail.Address;
import jakarta.mail.Message;
import jakarta.mail.internet.InternetAddress;
import javax.swing.tree.DefaultMutableTreeNode;

import com.darwinsys.lang.StringFormat;

/** A Mutable Tree Node that is also a Message. */
// tag::main[]
public class MessageNode extends DefaultMutableTreeNode {
	private static final long serialVersionUID = 1L;

	Message m;

	StringFormat fromFmt = new StringFormat(20, StringFormat.JUST_LEFT);
	StringFormat subjFmt = new StringFormat(30, StringFormat.JUST_LEFT);

	MessageNode(Message m) {
		this.m = m;
	}

	public String toString() {
		try {
			Address from = m.getFrom()[0];

			String fromAddress;
			if (from instanceof InternetAddress address)
				fromAddress = address.getAddress();
			else
				fromAddress = from.toString();

			StringBuffer sb = new StringBuffer();
			fromFmt.format(fromAddress, sb, null);
			sb.	append("  ");
			subjFmt.format(m.getSubject(), sb, null);
			return sb.toString();
		} catch (Exception e) {
			return e.toString();
		}
	}
}
// end::main[]
