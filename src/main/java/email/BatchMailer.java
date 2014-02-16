package email;

import java.io.*;
import java.util.*;
import javax.mail.*;

import com.darwinsys.mail.Mailer;

/** Command-line batch mailer */
public class BatchMailer {
	public static void main(String[] args) throws IOException {
		if (args.length != 3) {
			System.err.println("Usage: BatchMail subj template custlist");
			System.exit(1);
		}
		BatchMailer b = new BatchMailer();
		String subj = args[0];
		String template = args[1];
		String listfile = args[2];
		b.readTemplate(template);
		b.setSubject(subj);
		b.readCustList(listfile);
		b.sendMails();
	}

	/** The message */
	protected String messageBody;
	/** The subject */
	protected String subject = "Re: Your mail (what a lame subject)";

	/** Read the template file.
	  */
	public void readTemplate(String fileName) throws IOException {
		messageBody = null;
		BufferedReader is = new BufferedReader(new FileReader(fileName));
		String line;
		StringBuffer bs = new StringBuffer();
		while ((line = is.readLine()) != null) {
			bs.append(line).append("\n");
		}
		messageBody = bs.toString();
		is.close();
	}

	protected ArrayList<String> custList = new ArrayList<String>();

	/** Read the customer list. 
	  * Format: one customer email per line.
	  */
	public void readCustList(String fileName) throws IOException {
		BufferedReader is = new BufferedReader(new FileReader(fileName));
		String line;
		while ((line = is.readLine()) != null) {
			custList.add(line);
		}
		is.close();
	}

	public void setSubject(String s) {
		subject = s;
	}

	public void sendMails() {
		custList.forEach(customer -> {
			try {
				// This should be a bit more flexible :-(
				Mailer.send("mailhost", 
					customer, "http://www.darwinsys.com/", subject, messageBody);
				System.out.println(customer + " HANDOFF OK");
			} catch (MessagingException e) {
				System.out.println(customer + " failed: " + e.toString());
			}
		});
	}
}
