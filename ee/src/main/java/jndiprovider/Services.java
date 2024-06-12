package jndiprovider;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.StringTokenizer;

import javax.naming.Binding;
import javax.naming.CompositeName;
import javax.naming.Context;
import javax.naming.Name;
import javax.naming.NameClassPair;
import javax.naming.NameNotFoundException;
import javax.naming.NameParser;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.NotContextException;
import javax.naming.OperationNotSupportedException;

/**
 * A JNDI service provider that provides access to the /etc/services file.
 * The services file will be in the standard place on UNIX or WinNT,
 * or it must be given as the PROVIDER_URL properties.
 */
class Services implements Context {

	/** The traditional UNIX name for the services file */
	private static final String SERVICES_UNIX = "/etc/services";
	/** The "we invented it" Microsoft pathname for the same file */
	private static final String SERVICES_WNT = 
		"c:/winnt/system32/drivers/etc/services";

    private Hashtable<String,Object> myEnv;

    private Hashtable<String,Object> bindings = new Hashtable<String,Object>(100);

    static NameParser myParser = new FlatNameParser();

	/** Construct a Services object, given a Hashtable.
	 * This non-public constructor is expected to be called only from
	 * the ServicesContextFactory class.
	 */
    Services(Hashtable<String,Object> environment) {
		if (environment != null) {
			myEnv = new Hashtable<>(environment); 
		}

		/** Do the grunt work of reading the services file, stripping comments,
	 	 * parsing lines, and sticking into "bindings".
	 	 * For now only handles TCP; should have subdirectories for tcp and udp.
	 	 */
		String fileName = null;
		if (myEnv != null)
			fileName = (String)myEnv.get(PROVIDER_URL);
		if (fileName == null || fileName.equals(""))
			fileName = SERVICES_UNIX;
		if ("windows".equals(System.getProperty("os.name"))) {
			fileName = SERVICES_WNT;
		}

		String line = null;
		try {
			BufferedReader is = new BufferedReader(new FileReader(fileName));
			while ((line = is.readLine()) != null) {
				if (line.startsWith("#"))
					continue;
				StringTokenizer st = new StringTokenizer(line, " \t/");
				if (st.countTokens() < 3) {
					System.err.println("Bad line " + line);
					continue;
				}
				String service = st.nextToken();
				String port = st.nextToken();
				String proto = st.nextToken();
				// Convert the numeric string to an Integer wrapper object.
				if (proto.equals("tcp")) {
					bindings.put(service, Integer.valueOf(port));
				}
			}
			is.close();
		} catch (IOException ex) {
			throw new IllegalArgumentException(fileName + ": " + ex.toString());
		}
	}

	/** Look up a string in the bindings */
    public Object lookup(String name) throws NamingException {
        if (name.equals("")) {
            // Asking to look up this context itself.  Create and return
            // a new instance with its own independent environment.
            return (new Services(myEnv));
        }
        Object answer = bindings.get(name);
        if (answer == null) {
            throw new NameNotFoundException(name + " not found");
        }
        return answer;
    }

	/** Look up the given JNDI "Name" in the bindings */
    public Object lookup(Name name) throws NamingException {
        // Flat namespace; no federation; just call string version
        return lookup(name.toString()); 
    }

	/** The service is based on a static text file, which we don't
	 * intend to re-write, so don't allow binding.
	 */
    public void bind(String name, Object obj) throws NamingException {
		throw new OperationNotSupportedException(
			"Cannot bind in static services file");
    }

    public void bind(Name name, Object obj) throws NamingException {
        // Flat namespace; no federation; just call string version
        bind(name.toString(), obj);
    }

	/** Rebind; same as bind, can't really do it here */
    public void rebind(String name, Object obj) throws NamingException {
        bind(name, obj);
    }

	/** Rebind, same as bind; can't do it here */
    public void rebind(Name name, Object obj) throws NamingException {
        // Flat namespace; no federation; just call string version
        rebind(name.toString(), obj);
    }

    public void unbind(String name) throws NamingException {
		throw new OperationNotSupportedException(
			"Cannot unbind - static services file");
    }

    public void unbind(Name name) throws NamingException {
        // Flat namespace; no federation; just call string version
        unbind(name.toString());
    }

    public void rename(String oldname, String newname)
		throws NamingException {
		throw new OperationNotSupportedException(
			"Cannot rename - static services");
    }

    public void rename(Name oldname, Name newname)
            throws NamingException {
        // Flat namespace; no federation; just call string version
        rename(oldname.toString(), newname.toString());
    }

    public NamingEnumeration list(String name)
            throws NamingException {
        if (name.equals("")) {
            // listing this context
            return new FlatNames(bindings.keys());
        } 

        // Perhaps `name' names a context
        Object target = lookup(name);
        if (target instanceof Context) {
            return ((Context)target).list("");
        }
        throw new NotContextException(name + " cannot be listed");
    }

    public NamingEnumeration list(Name name)
            throws NamingException {
        // Flat namespace; no federation; just call string version
        return list(name.toString());
    }

    public NamingEnumeration listBindings(String name)
            throws NamingException {
        if (name.equals("")) {
            // listing this context
            return new FlatBindings(bindings.keys());
        } 

        // Perhaps `name' names a context
        Object target = lookup(name);
        if (target instanceof Context) {
            return ((Context)target).listBindings("");
        }
        throw new NotContextException(name + " cannot be listed");
    }

    public NamingEnumeration listBindings(Name name)
            throws NamingException {
        // Flat namespace; no federation; just call string version
        return listBindings(name.toString());
    }

    public void destroySubcontext(String name) throws NamingException {
        throw new OperationNotSupportedException(
                "Services does not support subcontexts");
    }

    public void destroySubcontext(Name name) throws NamingException {
        // Flat namespace; no federation; just call string version
        destroySubcontext(name.toString());
    }

    public Context createSubcontext(String name)
            throws NamingException {
        throw new OperationNotSupportedException(
                "Services does not support subcontexts");
    }

    public Context createSubcontext(Name name) throws NamingException {
        // Flat namespace; no federation; just call string version
        return createSubcontext(name.toString());
    }

    public Object lookupLink(String name) throws NamingException {
        // This flat context does not treat links specially
        return lookup(name);
    }

    public Object lookupLink(Name name) throws NamingException {
        // Flat namespace; no federation; just call string version
        return lookupLink(name.toString());
    }

    public NameParser getNameParser(String name)
            throws NamingException {
        return myParser;
    }

    public NameParser getNameParser(Name name) throws NamingException {
        // Flat namespace; no federation; just call string version
        return getNameParser(name.toString());
    }

    public String composeName(String name, String prefix)
            throws NamingException {
        Name result = composeName(new CompositeName(name),
                                  new CompositeName(prefix));
        return result.toString();
    }

    public Name composeName(Name name, Name prefix)
            throws NamingException {
        Name result = (Name)(prefix.clone());
        result.addAll(name);
        return result;
    }

    public Object addToEnvironment(String propName, Object propVal)
            throws NamingException {
        if (myEnv == null) {
            myEnv = new Hashtable<String,Object>(5, 0.75f);
	} 
	return myEnv.put(propName, propVal);
    }

    public Object removeFromEnvironment(String propName) 
            throws NamingException {
        if (myEnv == null)
            return null;

		return myEnv.remove(propName);
    }

    public Hashtable getEnvironment() throws NamingException {
		if (myEnv == null) {
			// Must return non-null
			return new Hashtable(5);
		} else {
			return (Hashtable)myEnv.clone();
		}
    }

    public String getNameInNamespace() throws NamingException {
		return ""; 
    }

    public void close() throws NamingException {
		myEnv = null;
		bindings = null;
    }

    /** Class for enumerating name/class pairs.
	 * Only needed because the API defines its own 
	 * enumeration class instead of using Enumeration.
	 */
    class FlatNames implements NamingEnumeration {
        Enumeration names;

        FlatNames (Enumeration names) {
            this.names = names;
        }

        public boolean hasMoreElements() {
            return names.hasMoreElements();
        }

        public boolean hasMore() throws NamingException {
            return hasMoreElements();
        }

        public Object nextElement() {
            String name = (String)names.nextElement();
            String className = bindings.get(name).getClass().getName();
            return new NameClassPair(name, className);
        }

        public Object next() throws NamingException {
            return nextElement();
        }
		public void close() {
		}
    }

    /** Class for enumerating bindings.
	 * Only needed because the API defines its own 
	 * enumeration class instead of using Enumeration.
	 */
    class FlatBindings implements NamingEnumeration {
        Enumeration names;

        FlatBindings (Enumeration names) {
            this.names = names;
        }

        public boolean hasMoreElements() {
            return names.hasMoreElements();
        }

        public boolean hasMore() throws NamingException {
            return hasMoreElements();
        }

        public Object nextElement() {
            String name = (String)names.nextElement();
            return new Binding(name, bindings.get(name));
        }

        public Object next() throws NamingException {
            return nextElement();
        }
		public void close() {
		}
    }
}
