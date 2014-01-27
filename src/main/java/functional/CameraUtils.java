package functional;

import java.util.Arrays;
import java.util.List;

public class CameraUtils {
	
	private static Camera[] list = {
			new Camera(CameraMake.Nikon, "D600", CameraType.DSLR, 2013, 1995, CameraSensor.FullFrame, 24),
			new Camera(CameraMake.Nikon, "1 S-1", CameraType.ILC, 2013, 499.95, CameraSensor.Tiny, 14.4),
			new Camera(CameraMake.Nikon, "P82", CameraType.P_S, 2013, 99.95, CameraSensor.Tiny, 14.4),
	};

	public static List<Camera> getList() {
		return Arrays.asList(list);
	}
}
