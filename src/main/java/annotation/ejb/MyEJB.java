package annotation.ejb;

import javax.ejb.*;

/** A trivial example showing how we could have Entity Bean
 * helper interfaces generated; here setName() is exposed to
 * both local and remote, while getName() is only exposed to local.
 */
public abstract class MyEJB implements EntityBean {

	// These are also CMP2 "virtual accessor" methods so must be abstract.

	/** Get the customer's name */
	@EjbInterface(REMOTE_BUSINESS) @EjbInterface(LOCAL_BUSINESS)
	public abstract void setName();

	/** Set the customer's name */
	@EjbInterface(LOCAL_BUSINESS) public abstract void getName();

	public void setEntityContext(EntityContext ctx) { }
	public void unsetEntityContext(){}

	public void ejbLoad(){}
	public void ejbStore(){}
	public void ejbPassivate(){}
	public void ejbActivate(){}
	public void ejbRemove() {}
}
