package structure;

public class MHPropertiesTest {
	public static void main(String[] args) throws java.io.IOException {
		MHProperties mp = new MHProperties();
		if (mp.size() == 0) {
			System.out.println("# No properties loaded from " +
				MHProperties.PROFILE_NAME);
		} else {
			mp.store(System.out, "Properties loaded from " +
				MHProperties.PROFILE_NAME);
		}
	}
}
