package structure50;

/**
 * Demonstrate some enums with String fields
 */
public class EnumWithStringFieldDemo {

	public static void main(String[] args) {

		// The simple "Recording" enum works but presentation is weak:
		RecordingCategory cat1 = RecordingCategory.JAZZ;
		System.out.println(cat1);
		RecordingCategory cat2 = RecordingCategory.valueOf("hip_hop".toUpperCase());
		System.out.println(cat2);

		// The better one works too but is more flexible and looks better:
		BetterRecordingCategory cat3 = BetterRecordingCategory.JAZZ;
		System.out.println(cat3);
		BetterRecordingCategory cat4 = BetterRecordingCategory.getCategory("Hip Hop");
		System.out.println(cat4);

	}

}
