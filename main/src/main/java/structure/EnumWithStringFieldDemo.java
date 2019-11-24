package structure;

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
		RecordingCategoryBetter cat3 = RecordingCategoryBetter.JAZZ;
		System.out.println(cat3);
		RecordingCategoryBetter cat4 = RecordingCategoryBetter.getCategory("Hip Hop");
		System.out.println(cat4);

	}

}
