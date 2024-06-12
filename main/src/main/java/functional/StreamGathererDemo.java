import java.nio.file.*;
import java.util.stream.*;

final int NUM_COUNTRIES = 10;

/** We have a bunch of grad students whom we want to
 * assign to do research on countries around the world.
 * Assume that each student can handle NUM_COUNTRIES countries
 * in this term.
 * The ISO-3166 2-letter country codes are in a text file.
 */
void main() throws Exception {

// tag::main[]
Files.lines(Path.of("country_codes.txt"))
	.gather(Gatherers.windowFixed(NUM_COUNTRIES))
	.limit(5)	// Just sample
	.forEach(System.out::println);
// end::main[]
}
