import javax.ejb.*;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class HelloEJB implements SessionBean {

	/* Say Hello! */
	public String sayHello(String name) {
		String msg;
		try {
			msg = (String)new InitialContext().lookup("java:comp/env/message");
		} catch (NamingException ex) {
			msg = ex.toString();
		}
		return msg + " " + name;
	}

	public void ejbCreate() {}
	public void ejbRemove() {}
	public void ejbActivate() {}
	public void ejbPassivate() {}
	public void setSessionContext(SessionContext ctx) {}
}
