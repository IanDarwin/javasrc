package reflection.cooklet;

/** A simple class, just to provide the list of methods that 
 * users need to provide to be usable in our application.
 * Note that the class is abstract so you must subclass it,
 * but the methods are non-abstract so you don't have to provide
 * dummy versions if you don't need a particular functionality.
 */
public abstract class Cooklet {

	/** The initialization method. The Cookie application will
	 * call you here (AFTER calling your no-argument constructor)
	 * to allow you to initialize your code
	 */
	public void initialize() {
	}

	/** The work method. The cookie application will call you
	 * here when it is time for you to start cooking.
	 */
	public void work() {
	}

	/** The termination method. The cookie application will call you
	 * here when it is time for you to stop cooking and shut down
	 * in an orderly fashion.
	 */
	public void terminate() {
	}
}
