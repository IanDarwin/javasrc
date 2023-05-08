package functional_med;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;

import functional_med.data.MockData;
import functional_med.model.Reading;
import functional_med.model.ReadingType;

/**
 *  Badly named Functional Programming for Medical Readings Demo;
 *  processes a list of Reading objects.
 */
public class FPMedDemo {
	static List<Reading> listOfReadings;
	
	public static void main(String[] args) {
		version1();
		version2();
		version3();
		version3b();
	}
	
	/** Process the list one way */
	public static void version1() {
		System.out.println("V1");
		List<Reading> lowBgReadings = search(r -> r.type == ReadingType.BG && (r.value1 < 2.8 || r.value1 > 15));
		lowBgReadings.forEach(System.out::println);
	}

	/** Process the list another way */
	public static void version2() {
		System.out.println("V2");
		search(r -> r.type == ReadingType.BG && (r.value1 < 2.8 || r.value1 > 15)).forEach(System.out::println);
	}

	/** Process the list yet another way */
	public static void version3() {
		System.out.println("V3");
		listOfReadings.stream().filter(
				r -> r.type == ReadingType.BG && (r.value1 < 2.8 || r.value1 > 15))
				.sorted()	
				.forEach(System.out::println);
	}

	/** Process the list yet another way */
	public static void version3b() {
		System.out.println("V1");
		listOfReadings.stream().filter(
				r -> r.type == ReadingType.BG && (r.value1 < 2.8 || r.value1 > 15))
				.sorted(Comparator.comparing(r -> r.when))
				.forEach(o->System.out.println(o));
	}

	public static List<Reading> search(Predicate<Reading> tester) {
        return listOfReadings.stream().filter(tester).toList();
    }
	
	static {
		listOfReadings = Arrays.asList(MockData.data);
	}
}
