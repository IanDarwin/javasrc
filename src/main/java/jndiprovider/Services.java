/*
 * Copyright (c) Ian F. Darwin, ian@darwinsys.com, 1996-2002.
 * All rights reserved. Software written by Ian F. Darwin and others.
 * $Id$
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 * 1. Redistributions of source code must retain the above copyright
 *    notice, this list of conditions and the following disclaimer.
 * 2. Redistributions in binary form must reproduce the above copyright
 *    notice, this list of conditions and the following disclaimer in the
 *    documentation and/or other materials provided with the distribution.
 * 3. All advertising materials mentioning features or use of this software
 *    must display the following acknowledgement:
 *        This product includes software developed by Ian F. Darwin.
 * 4. Neither the name of the author nor the names of its
 *    contributors may be used to endorse or promote products derived
 *    from this software without specific prior written permission.
 * 
 * The inner classes FlatNames and FlatBindings are from the JNDI
 * sample code, Copyright 1997, 1998, 1999 Sun Microsystems, Inc.
 * All Rights Reserved.
 *
 * Sun grants you ("Licensee") a non-exclusive, royalty free,
 * license to use, modify and redistribute this software in source and
 * binary code form, provided that i) this copyright notice and license
 * appear on all copies of the software; and ii) Licensee does not 
 * utilize the software in a manner which is disparaging to Sun.
 *
 * THIS SOFTWARE IS PROVIDED BY THE AUTHOR AND CONTRIBUTORS ``AS IS''
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED
 * TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
 * PURPOSE ARE DISCLAIMED.  IN NO EVENT SHALL THE AUTHOR OR CONTRIBUTORS
 * BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGE.
 * 
 * Java, the Duke mascot, and all variants of Sun's Java "steaming coffee
 * cup" logo are trademarks of Sun Microsystems. Sun's, and James Gosling's,
 * pioneering role in inventing and promulgating (and standardizing) the Java 
 * language and environment is gratefully acknowledged.
 * 
 * The pioneering role of Dennis Ritchie and Bjarne Stroustrup, of AT&T, for
 * inventing predecessor languages C and C++ is also gratefully acknowledged.
 */

package com.darwinsys.jndi;

import java.io.*;
import java.util.*;
import javax.naming.*;

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

    private Hashtable myEnv;

    private Hashtable bindings = new Hashtable(100);

    static NameParser myParser = new FlatNameParser();

	/** Construct a Services object, given a Hashtable.
	 * This non-public constructor is expected to be called only from
	 * the ServicesContextFactory class.
	 */
    Services(Hashtable environment) {
		if (environment != null)
			myEnv = (Hashtable)(environment.clone()); 

		/** Do the grunt work of reading the services file, stripping comments,
	 	 * parsing lines, and sticking into "bindings".
	 	 * For now only handles TCP; should have subdirectories for tcp and udp.
	 	 */
		String fileName = null;
		if (myEnv != null)
			fileName = (String)myEnv.get(PROVIDER_URL);
		if (fileName == null || fileName.equals(""))
			fileName = SERVICES_UNIX;
		// XXX handle Windows too -- System.getProperty("os.name")

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
					bindings.put(service, new Integer(port));
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
            myEnv = new Hashtable(5, 0.75f);
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
			return new Hashtable(3, 0.75f);
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
