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
		List<Reading> lowBgReadings = search1(r -> r.type == ReadingType.BG && (r.value1 < 2.8 || r.value1 > 15));
		lowBgReadings.forEach(r -> System.out.println(r));
	}

	/** Process the list another way */
	public static void version2() {
		search2(r -> r.type == ReadingType.BG && (r.value1 < 2.8 || r.value1 > 15)).forEach(r -> System.out.println(r));
	}

	/** Process the list yet another way */
	public static void version3() {
		listOfReadings.stream().filter(
				r -> r.type == ReadingType.BG && (r.value1 < 2.8 || r.value1 > 15))
				.sorted()	
				.forEach(o->System.out.println(o));
	}
	
	/** Process the list yet another way - working version (comment out call to version3!) */
	public static void version3b() {
		listOfReadings.stream().filter(
				r -> r.type == ReadingType.BG && (r.value1 < 2.8 || r.value1 > 15))
				.sorted((r1, r2) -> r1.when.compareTo(r2.when))
				.forEach(o->System.out.println(o));
	}
	
	public static List<Reading> search1(ReadingAcceptor tester) {
        List<Reading> results = new ArrayList<>();
        listOfReadings.forEach(c -> {
            if (tester.test(c))
                results.add(c);
        });
        return results;
    }
	
	public static List<Reading> search2(Predicate<Reading> tester) {
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
