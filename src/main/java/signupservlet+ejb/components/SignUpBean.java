package components;

import javax.ejb.*;

/**
 *  Implementation Class for SignUp.
 *
 * @author MkBean
 */
public class SignUpBean
 						implements EntityBean
	{

	// fields.
	public String email;
	public String name;

	// implementation of main logic

	public void setemail(String x) {
		this.email = x;
	}
	public String getemail() {
		return email;
	}
	// Create Methods
	// method implementation for Create/PostCreate
	public SignUp ejbCreate(String name, String email)
								throws CreateException{
		this.name = name;
		this.email = email;
 		if (this.name == null)
 		{
			throw new CreateException();
		}
 		return null;
 	}

	public SignUp ejbPostCreate(String name, String email){
 		return null;
 	}

 	// override the toString () method
	public String toString () {
		return "name: " + name + " email: " + email;
	}

	// Find methods will be implemented by deployment.

	/** reference to passed Session context */
	private EntityContext ctx;

	// method implementations imposed by EntityBean interface

	/** save the session context */
	public void setEntityContext(EntityContext x) { ctx = x; }
	public void unsetEntityContext() { ctx = null; }
	public void ejbLoad() {}	// entity
	public void ejbStore() {}	// entity
	public void ejbActivate() { name = (String)ctx.getPrimaryKey();}
	public void ejbPassivate() { name = null; email = null; }
	public void ejbRemove() {}

}
