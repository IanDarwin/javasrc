import javax.ejb.*;
import javax.rmi.*;

public class HelloEJB implements SessionBean {

	/* Say Hello! */
	public String sayHello(String name) {
		return "Hello, " + name;
	}

	public void ejbCreate() {}
	public void ejbRemove() {}
	public void ejbActivate() {}
	public void ejbPassivate() {}
	public void setSessionContext(SessionContext ctx) {}
}
