package functional;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class CameraSearchPredicate {
	
	List<Camera> privateListOfCameras = CameraUtils.getList();
	
	// BEGIN search
	public List<Camera> search(Predicate<Camera> tester) {
	    List<Camera> results = new ArrayList<>();
	    privateListOfCameras.forEach(c -> {
	        if (tester.test(c))
	            results.add(c);
	    });
	    return results;
	}
	// END search
	
	public static void main(String[] args) {
		CameraSearchPredicate searchApp = new CameraSearchPredicate();
		List<Camera> results = searchApp.search(c -> c.isIlc() && c.getPrice() < 500);
		System.out.println(results);
	}
}
