package functional;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class CameraSearchUsingPredicate {
	
	List<Camera> privateListOfCameras;
	
	public List<Camera> search(Predicate<Camera> tester) {
	    List<Camera> results = new ArrayList<>();
	    for (Camera c : privateListOfCameras) {
	        if (tester.test(c)) {
	            results.add(c);
	        }
	    }
	    return results;
	}
}
