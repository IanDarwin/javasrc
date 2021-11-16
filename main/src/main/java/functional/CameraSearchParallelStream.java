package functional;

import java.util.List;

public class CameraSearchParallelStream {
	
	static List<Camera> privateListOfCameras = CameraUtils.getList();
	
	// tag::search[]
	public static void main(String[] args) {
		System.out.println("Search Results using For Loop");
		for (Object camera : privateListOfCameras.parallelStream(). // <1>
				filter(c -> c.isIlc() && c.price() < 500).       // <2>
				toArray()) {                                        // <3>
			System.out.println(camera);                             // <4>
		}

		System.out.println(
			"Search Results from shorter, more functional approach");
		privateListOfCameras.parallelStream().                      // <5>
                filter(c -> c.isIlc() && c.price() < 500).
				forEach(System.out::println);
	}
	// end::search[]
}
