package functional_med;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

import functional_med.data.MockData;
import functional_med.data.ReadingAcceptor;
import functional_med.model.Reading;
import functional_med.model.ReadingType;

public class Demo {
	static List<Reading> listOfReadings;
	
	public static void main(String[] args) {
		version1();
		//version2();
		//version3();
		//version3b();
	}
	
	/** Process the list one way */
	public static void version1() {
		List<Reading> lowBgReadings = search(r -> r.type == ReadingType.BG && (r.value1 < 2.8 || r.value1 > 15));
		lowBgReadings.forEach(System.out::println);
	}

	/** Process the list another way */
	public static void version2() {
		search(r -> r.type == ReadingType.BG && (r.value1 < 2.8 || r.value1 > 15)).forEach(System.out::println);
	}

	/** Process the list yet another way */
	public static void version3() {
		listOfReadings.stream().filter(
				r -> r.type == ReadingType.BG && (r.value1 < 2.8 || r.value1 > 15))
				.sorted()	
				.forEach(System.out::println);
	}

	/** Process the list yet another way */
	public static void version3b() {
		listOfReadings.stream().filter(
				r -> r.type == ReadingType.BG && (r.value1 < 2.8 || r.value1 > 15))
				.sorted((r1, r2) -> r1.when.compareTo(r2.when))
				.forEach(o->System.out.println(o));
	}

	public static List<Reading> search(Predicate<Reading> tester) {
        List<Reading> results = new ArrayList<>();
        listOfReadings.forEach(c -> {
            if (tester.test(c))
                results.add(c);
        });
        return results;
    }
	
	static {
		listOfReadings = Arrays.asList(MockData.data);
	}
}
