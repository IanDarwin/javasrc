import javax.mail.*;
import java.util.*;

/** Command-line batch mailer */
public class BatchMailer {
	public static void main(String[] args) throws IOException {
		BatchMailer b = new BatchMailer();
		b.setSubject("OpenBSD");
		b.readTemplate();
		b.readCustList();
		b.sendMails();
	}

	String messageBody;

	/** Read the template file.
	  * For now, hard-code name.
	  */
	public void readTemplate() throws IOException {
		messageBody = null;
		BufferedReader is = new BufferedReader(new FileReader("to.obsd.cust");
		String line;
		StringBuffer bs = new StringBuffer();
		while ((line = is.readLine() != null) {
			bs.append(line).append("\n");
		}
		is.close();
	}

	ArrayList custList = new ArrayList();

	/** Read the customer list. 
	  * For now, hard-code name.
	  * For now, one customer email per line.
	  */
	public void readCustList() throws IOException {
		messageBody = null;
		BufferedReader is = new BufferedReader(new FileReader("obsd.custlist");
		String line;
		StringBuffer bs = new StringBuffer();
		while ((line = is.readLine() != null) {
			custList.add(line);
		}
		is.close();
	}

	public void sendMails() {
		Iterator it = custList.iterator();
		while (it.hasNext()) {
			string customer = (String)it.next();
			try {
				Mailer.send("mailhost", 
					customer, "ian@darwinsys.com", "OpenBSD")
			} catch (MailException e) {
				System.out.println(customer + " barfed: " + e.toString());
			}
		}
	}
}
