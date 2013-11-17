package functional;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class CameraSearchUsingPredicate {
	
	List<Camera> privateListOfCameras = CameraUtils.getList();
	
	public List<Camera> search(Predicate<Camera> tester) {
	    List<Camera> results = new ArrayList<>();
	    for (Camera c : privateListOfCameras) {
	        if (tester.test(c)) {
	            results.add(c);
	        }
	    }
	    return results;
	}
	
	public static void main(String[] args) {
		CameraSearchUsingPredicate searchApp = new CameraSearchUsingPredicate();
		List<Camera> results = searchApp.search(c -> c.isIlc() && c.getPrice() < 500);
		System.out.println(results);
	}
}
