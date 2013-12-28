package jndi;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

/** Example of lookup up filenames in a JNDI "file system context".
 * N.B. Will ONLY work if you have the "File System Context Provider" JAR
 * on your CLASSPATH!!
 */
public class LookupFile {

	/** The class name for the FileSystemContext service provider */
	final static String SP = "com.sun.jndi.fscontext.RefFSContextFactory";

	public static void main(String[] av) throws NamingException {
		LookupFile lf = new LookupFile();
		for (int i=0; i<av.length; i++)
			System.out.println(av[i] + "-->" + lf.look(av[i]));
	}

	/** The naming context */
	Context ctx = null;

	/** Constructor */
	public LookupFile() throws NamingException {
		System.getProperties().setProperty(Context.INITIAL_CONTEXT_FACTORY, SP);
		System.getProperties().setProperty(Context.PROVIDER_URL, "/");

		// Create the initial context
		ctx = new InitialContext();
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
