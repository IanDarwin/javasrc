import java.util.*;
import javax.naming.*;

/** Example of lookup up filenames in a JNDI "file system context".
 * @version $Id$
 */
public class LookupFile {
	Hashtable env = new Hashtable();

	public static void main(String[] av) {
		LookupFile lf = new LookupFile();
		for (int i=0; i<av.length; i++)
			System.out.println(av[i] + "-->" + lf.look(av[i]));
	}

	/** The service provider */
	final static String SP = "com.sun.jndi.fscontext.RefFSContextFactory";

	/** The naming context */
	Context ctx = null;

	/** Constructor */
	public LookupFile() {
		env.put(Context.INITIAL_CONTEXT_FACTORY, SP);

		try {
			// Create the initial context
			ctx = new InitialContext(env);
		} catch (NamingException e) {
			System.err.println("Problem Creating Context with " + SP);
		}
	}

	/** Look up an object in the filesystem. */
	protected String look(String name) {
		try {
			Object obj = ctx.lookup(name);

			/* Got something? Return it. */
			return obj.toString();

		} catch (NamingException e) {
			System.err.println("Problem looking up " + name + ": " + e);
			return null;
		}
	}
}
