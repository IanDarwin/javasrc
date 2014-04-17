package oo;

/** Demo of a data class that will be used as a JavaBean or as a data
 * class in a Servlet container; marking it as Serializable allows
 * it to be saved ("serialized") to disk or over a network connection.
 */
// BEGIN main
public class SerializableUser implements java.io.Serializable {
	public String name;
	public String address;
	public String country;
	public String phoneNum;

	// other fields, and methods, here...
    static final long serialVersionUID = -7978489268769667877L;
}
// END main
