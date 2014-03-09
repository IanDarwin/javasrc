package functional;

import java.util.List;

public class CameraSearchParallelStream {
	
	static List<Camera> privateListOfCameras = CameraUtils.getList();
	
	// BEGIN search
	public static void main(String[] args) {
		for (Object camera : privateListOfCameras.parallelStream(). // <1>
				filter(c -> c.isIlc() && c.getPrice() < 500).       // <2>
				toArray()) {                                        // <3>
			System.out.println(camera);                             // <4>
		}
	}
	// END search
}
