package lang;

// The Overall Structure of a Java Class

//package com.acme.stuff;

import java.util.Calendar;

public class Overall extends Object implements Fun, Action {

	int num;		// object-wide data
	float tmp;		// (each X object has own num, tmp)

	public Overall() {	// Constructor - same name as class
		// initializations...
	}

	public int abc() {		// ordinary method
		int i;					// local data
		int answer = 0;
		for (i=0; i<num; i++) {
			// compute answer
		}
		return answer;
	}

	Calendar xyz;				// other object-wide data

	public void def() {
		// more computation, presumably using xyz
	}
}

// Stuff below here is just to get this to compile.

interface Fun {
}
interface Action {
}
