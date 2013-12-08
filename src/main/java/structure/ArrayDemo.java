package structure;

/** Try to collect all the Array Chapter examples from 471 into one file
 * @author Ian Darwin
 */
public class ArrayDemo  {
	@SuppressWarnings("unused")
	public static void main(String[] argv) {
		int ages[];			// declare a reference
		ages = new int[10];		// construct it
		int ages2[] = new int[10];	// short form
		// an even shorter form is the initializer form:
		int monthLen[] = {
				31, 28, 31, 30,
				31, 30, 31, 31,
				30, 31, 30, 31
		};
		
		final int MAX = 10;
		Employee staff[] = new Employee[MAX];
		for (int i=0; i<MAX; i++)
			   staff[i] = new Employee("NoName", i);
	 
		// Two-Dimensional Arrays
		// Want a 10-by-24 array
		int me[][] = new int[10][];
		for (int i=0; i<10; i++)
			me[i] = new int[24];

		System.out.println(me.length);
		System.out.println(me[0].length);

		int me2[][];				// shorter
		me2 = new int[10][24];
		
		int me3[][] = new int[10][24];	// shortest

		double data[][] = {
			{24, 100},
			{10, 20},
			{32, 98.6},
		};
		System.out.println("Should print 98.6: " + data[2][1] );

		int nEmployees = 0;
		// allocate array
		staff = new Employee[MAX];
		staff[nEmployees++] = new Employee();// allocate Employee an object
		if (nEmployees >= staff.length) {
			System.err.println("Too Many Staff Hired!!");
			System.exit(1);  // wimp out
		}

		// better to reallocate, making the data structure dynamic
		if (nEmployees >= staff.length) {
			Employee tmpstaff[] = new Employee[staff.length + 10];
			for (int i=0; i<staff.length; i++) { //or System.arraycopy()
					tmpstaff[i] = staff[i];
			}
			staff = tmpstaff;       // copies the array reference
		   // old array will be garbage collected soon...
		}
	}

}
