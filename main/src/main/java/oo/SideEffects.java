package oo;

import java.util.*;

/**
 * Simple demo of avoiding side-effects by using Object.clone()
 * to duplicate an object before passing it to your enemy's methods.
 * Cloneable is a "marker" interface: it has no methods, but is tested
 * for by Object.clone. If you implement it, you tell Object.clone that
 * your data is stable enough that field-by-field copy is OK.
 */
class Enemy {
	public void munge(SideEffects md) {
		System.out.println("Object is " + md);
		md.year = 0;
		md.td.set(Calendar.YEAR, 1971);
	}
}

public class SideEffects implements Cloneable {
	/** When we clone a "SideEffects", this REFERENCE gets cloned */
	public Calendar td;	
	/** When we clone a "SideEffects", this integer does NOT get cloned */
	volatile int year;

	public static void main(String[] argv) throws CloneNotSupportedException {
		new SideEffects().process();
	}

	SideEffects() {
		td = Calendar.getInstance();	// today
		year = td.get(Calendar.YEAR);
	}

	public void process() throws CloneNotSupportedException {
		Enemy r = new Enemy();
		System.out.println("We have seen the enemy, and he is " + r);
		System.out.println("Today is " + td.getTime() + "; nice weather, isn't it?");
		System.out.println("And the year is " + year);
		r.munge((SideEffects)this.clone());
		System.out.println("Why, I believe it is now " + td.getTime());
		if (year == 0)		// should not happen!!
			System.out.println("** PANIC IN YEAR ZERO **");
		System.out.println("But wait, the year is still " + year);
		r.munge(this);
		System.out.println("Now I'm certain that it's " + td.getTime());
		System.out.println("Now the year is  " + year);
	}
}
