package ca.tcp.utils;

import java.util.List;
import java.io.ObjectStreamException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import ca.tcp.controller.Direction;

/**
 * Top-level class for Enumerations implementing Bloch's Typesafe Enum pattern,
 * similar to how he extended it for Java 1.5 (with valueOf() method), but
 * implemented entirely using pre-1.5 mechanisms and syntax.
 * When we move to 1.5, change all subclasses of this to J2SE 1.5 enums.
 * See Java Cookbook, 2nd Edition, Chapter 8.
 * See http://www.javaworld.com/javaworld/javatips/jw-javatip122.html for
 * info on Serializable and readResolve(); objects used in HttpSessions
 * are required to be Serializable.
 * <p>
 * A sample use is shown here:<pre>
 * /**
 *  * An Enum for Upload/Download Direction
 *  *
 * public class Direction extends Enum {
 * 	public static final String KLASSNAME = "Direction";
 * 	
 * 	/** Constructor must be private to ensure typesafe enumeration pattern *
 * 	private Direction(String status) {
 * 		super(KLASSNAME, status);
 * 	}
 * 	public Direction valueOf(String val) {
 * 		return (Direction)Enum.getValueOf(KLASSNAME, val);
 * 	}
 * 	
 * 	/** The status value for the INPUT direction, that is, a file checkin or upload *
 * 	public final static Direction DIRECTION_IN = new Direction("I");
 * 	/** The status value for the OUTPUT direction, that is, a file checkout or download *
 * 	public final static Direction DIRECTION_OUT = new Direction("O");
 * }
 */
public abstract class Enum implements Serializable {
	/** The name of this class, set in constructor. */
	protected String className;
	/** The value of this instance */
	private String value;

	/** This maps from each class's Class object to its List of subclasses */
	private static Map map = new HashMap();
	
	/** Get the list for this Class */
	private static List getList(String klass) {
		return (List)map.get(klass);
	}
	
	/** Although this is public, the implementing subclass' constructor must be 
	 * private to ensure typesafe enumeration pattern.
	 */
	public Enum(String klass, String val) {
		className = klass;
		value = val;
		List l = (List)map.get(klass);
		if (l == null) {
			map.put(klass, l = new ArrayList());
		}
		l.add(this);
	}
	
	/** Returns the value of this Enum as a String */
	public String toString() {
		return value;
	}

	/** Returns the given Enum instance for the given String.
	 * @throws IllegalArgumentException if the input is not one of the valid values.
	 */
	public static Enum getValueOf(String klass, String val) {
		List l = getList(klass);
		for (int i = 0; i < l.size(); i++) {
			Enum e = (Enum)l.get(i);
			if (e.value.equals(val))	{
				return e;
			}
		}
		throw new IllegalArgumentException("Value '" + val + "' is not a valid " + klass + " enumeration value.");
	}

	/** Return all the values of this Enumeration */
	public Enum[] values(String klass) {
		List l = getList(klass);
		return (Enum[]) l.toArray(new Enum[l.size()]);
	}
	
	/** Needed to avoid having Serialization create objects that bypass the constructor */
    protected Object readResolve() throws ObjectStreamException
    {
    	System.out.println("readResolve: value = " + value);
        return getValueOf(className, value);
    }
}
