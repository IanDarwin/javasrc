import java.io.*;
import java.util.*;
import javax.mail.*;

/** Command-line batch mailer */
public class BatchMailer {
	public static void main(String[] args) throws IOException {
		BatchMailer b = new BatchMailer();
		b.readTemplate();
		b.readCustList();
		b.sendMails();
	}

	protected String messageBody;

	/** Read the template file.
	  * For now, hard-code name.
	  */
	public void readTemplate() throws IOException {
		messageBody = null;
		BufferedReader is = new BufferedReader(new FileReader("template.txt"));
		String line;
		StringBuffer bs = new StringBuffer();
		while ((line = is.readLine()) != null) {
			bs.append(line).append("\n");
		}
		messageBody = bs.toString();
		is.close();
	}

	protected ArrayList custList = new ArrayList();

	/** Read the customer list. 
	  * For now, hard-code name.
	  * For now, one customer email per line.
	  */
	public void readCustList() throws IOException {
		BufferedReader is = new BufferedReader(new FileReader("custlist.txt"));
		String line;
		while ((line = is.readLine()) != null) {
			custList.add(line);
		}
		is.close();
	}

	public void sendMails() {
		Iterator it = custList.iterator();
		while (it.hasNext()) {
			String customer = (String)it.next();
			try {
				Mailer.send("mailhost", 
					customer, "ian@darwinsys.com", "OpenBSD", messageBody);
				System.out.println(customer + " HANDOFF OK");
			} catch (MessagingException e) {
				System.out.println(customer + " barfed: " + e.toString());
			}
		}
	}
}
