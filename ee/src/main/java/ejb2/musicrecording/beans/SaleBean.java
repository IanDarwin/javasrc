package ejb2.musicrecording.beans;

import java.util.Collection;

import javax.ejb.SessionBean;
import javax.ejb.SessionContext;
import javax.naming.InitialContext;

/**
 * Implementation of the "put beans on sale" Session Facade.
 * @author Ian Darwin.
 */
public class SaleBean implements SessionBean {

	private static final long serialVersionUID = 749376926661614662L;
	
	/* Say Hello */
	public String sayHello(String name) {
		return "Hello" + " " + name;
	}

	/**
	 * Put all the recordings on sale by the given percentage.
	 * Non-idempotent; at present there is a single price column;
	 * there should be a base price and a "current sale price".
	 */
	public boolean markDown(double percentage) {
		try {
			System.out.println("Holding sale of " + percentage + "%.");
			MusicLocalHome home = (MusicLocalHome)
				new InitialContext().lookup("MusicRecordingLocal");
			System.out.println("Got LocalHome!");
			Collection<MusicLocal> recordings = home.findAll();
			for (MusicLocal m : recordings) {
				double p = m.getPrice();
				double np = p - (p * (percentage/100));
				System.out.println(
					String.format(
						"Marking down %s from %5.2f to %5.2f",
						m.getTitle(), p, np));
				m.setPrice(np);
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return true;
	}

	public void ejbCreate() {}
	public void ejbRemove() {}
	public void ejbActivate() {}
	public void ejbPassivate() {}
	public void setSessionContext(SessionContext ctx) {}
}
