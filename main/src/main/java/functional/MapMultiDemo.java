package functional;

import java.util.*;
import java.util.stream.*;

/**
 * Collectors.mapMulti is like flatmap but allows to create a different-sized list.
 * It is basically
 * <R> Stream<R> mapMulti(BiConsumer<T, Consumer<R>> mapper)
 * Each time we call the consumer's accept() method, the value
 * is added to the output Stream.
 * Nothing you couldn't do with filter(), map() etc but more efficient.
 */
public class MapMultiDemo {
	public static void main(String[] args) {
		List<Double> usPrices = List.of(100d, 250d, 0d, 400d, 1200d, 6000d);
		System.out.println(usPrices);
		double averagePrice = 0;
		List<Double> cdnPrices = usPrices.stream().
			<Double>mapMulti((price,consumer) -> { 
				if (price > 0) {
					consumer.accept(price*1.25);
				}
			})
			.collect(Collectors.toList());
		System.out.println(cdnPrices);
	}
}
