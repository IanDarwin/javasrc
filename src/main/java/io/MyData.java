package io;

import java.io.Serializable;

/** Simple data class used in Serialization demos. */
public class MyData implements Serializable {
	String userName;
	String passwordCypher;
	transient String passwordClear;

	/** This constructor is required for use by JDO */
	public MyData() {
	}

	public MyData(String name, String clear) {
		userName = name;
		// Save the clear text p/w in the object, it won't get serialized
		passwordClear = clear;
		// So we must save the encryption! Encryption not shown here.
		passwordCypher = passwordClear;
	}

	public String toString() {
		return "MyData[" + userName + ",XXXXX]";
	}
}
