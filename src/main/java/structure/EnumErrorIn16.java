package structure;

/**
 * Demonstrate an Enum with Enum fields AND show an
 * "issue" introduced in 1.6: if you interchange the
 * two definitions VAL1 and VAL2 below, it will
 * give a fatal compile ERROR in 1.6, but not in 1.5.
 */
public enum EnumErrorIn16 {

	VAL2("Goodbye", null), // OK if first, fails on 1.6 if second
	VAL1("Hello", VAL2),
	;
	
	EnumErrorIn16(String name, EnumErrorIn16 next) {
		this.name = name;
		this.next = next;
	}

	private String name;
	private EnumErrorIn16 next;
	
	public String getName() {
		return name;
	}
	public EnumErrorIn16 getNext() {
		return next;
	}
}
