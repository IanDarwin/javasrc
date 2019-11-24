package io.lookup;

/*
0) FIX THE BUG: Classes that conflict get only one entry!
e.g., try "Date" - do you get java.util.Date or java.sql.Date?
Suggestion: use a Hashmap (like the mini-Web Server did) to keep
the entries and, when storing, if something was already there,
replace it with a special object, say "Collision". 
Then, when you dump the hashtable,
if an entry is an instanceof Collision, don't output it.
*/

	public class OPTIONS  /*rename to MakeList*/  {
		class Collision {
			// no members or fields needed.
		}
		// rest of MakeList class.
	}
/*
Extend the program to:

1) Accept the INPUT filename from the command line.

2) You are currently not getting the I18N entries, from 
file /jdk1.x/jre/lib/i18n.jar. Here's how to fix this:

Lookup the java.lang.boot.classpath from System.properties.

Lookup the path.separator from there too. Use it to break the
former into a list of jar files.  Process each one.
*/
