package ejb2.hello.src;

import javax.ejb.SessionBean;
import javax.ejb.SessionContext;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class HelloEJB implements SessionBean {

	private static final long serialVersionUID = 1749376925921614662L;
	
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
