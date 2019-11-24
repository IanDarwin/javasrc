package lang;

import java.io.ObjectStreamException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Top-level class for Enumerations implementing Bloch's Typesafe Enum pattern,
 * similar to how he implemented it for Java 1.5 (with valueOf() method), but
 * implemented entirely using pre-1.5 mechanisms and syntax.
 * When you move to Java 5, eschew this in favor of the built-in version..
 * See Java Cookbook, 2nd Edition, Chapter 8.
 * See http://www.javaworld.com/javaworld/javatips/jw-javatip122.html for
 * info on Serializable and readResolve(); objects used in HttpSessions
 * are required to be Serializable.
 * <p>
 * A sample use is shown here:<pre>
 * /**
 *  * An Enum for Upload/Download Direction
 *  *
 * public class Direction extends TypesafeEnum {
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
 * }</pre>
 * @author Ian; developed at the Toronto Centre for Phenogenomics.
 */
@Deprecated
public abstract class TypesafeEnum implements Serializable {

	private static final long serialVersionUID = 1L;
	
	/** The name of this class, set in constructor. */
	protected String className;
	/** The value of this instance */
	private String value;

	/** This map, shared by all subclasses, maps from each class's Class object to its List of subclasses */
	private static Map<String,List<TypesafeEnum>> map = new HashMap<String,List<TypesafeEnum>>();
	
	/** Get the list for this Class */
	private static List<TypesafeEnum> getList(String klass) {
		return map.get(klass);
	}
	
	/** Although this is public, the implementing subclass' constructor must be 
	 * private to ensure typesafe enumeration pattern.
	 */
	public TypesafeEnum(String klass, String val) {
		className = klass;
		value = val;
		List<TypesafeEnum> l = map.get(klass);
		if (l == null) {
			map.put(klass, l = new ArrayList<TypesafeEnum>());
		}
		l.add(this);
	}
	
	/** Returns the value of this Enum as a String */
	public String toString() {
		return value;
	}

	/** Returns the given Enum instance for the given String. It is expected that the subclass will 
	 * use this in a valueOf() method with a more narrow return type, e.g.,
	 * <pre>public static Direction valueOf(String val) {
	 * 	return (Direction)Enum.getValueOf(KLASSNAME, val);
	 * }</pre>
	 * @throws IllegalArgumentException if the input is not one of the valid values.
	 */
	public static TypesafeEnum getValueOf(String klass, String val) {
		List<TypesafeEnum> l = getList(klass);
		for (int i = 0; i < l.size(); i++) {
			TypesafeEnum e = (TypesafeEnum)l.get(i);
			if (e.value.equals(val))	{
				return e;
			}
		}
		throw new IllegalArgumentException("Value '" + val + "' is not a valid " + klass + " enumeration value.");
	}

	/** Return all the values of this Enumeration */
	public TypesafeEnum[] values(String klass) {
		List<TypesafeEnum> l = getList(klass);
		return (TypesafeEnum[]) l.toArray(new TypesafeEnum[l.size()]);
	}
	
	/** Needed to avoid having Serialization create objects that bypass the constructor */
    protected Object readResolve() throws ObjectStreamException
    {
    	System.out.println("readResolve: value = " + value);
        return getValueOf(className, value);
    }
}
