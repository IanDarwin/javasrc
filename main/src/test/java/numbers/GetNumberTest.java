package numbers;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

class GetNumberTest {

	@ParameterizedTest(name = "{index} String {0} should give {1}")
	@MethodSource("provideArgsForProcess")
	final void process(Object oo, String str) {
		assertEquals(oo, GetNumber.process(str));
	}

	static Stream<Arguments> provideArgsForProcess() {
		return Stream.of(
				//           Expected Value   String Input
				Arguments.of(Long.valueOf(0), "0"),
				Arguments.of(Long.valueOf(1), "1"),
				Arguments.of(Long.valueOf(-1),"-1"),
				Arguments.of(42L,             "42"),
				Arguments.of(-3.14592857D,    "-3.14592857D"),
				Arguments.of(42D,             "42.0"),
				Arguments.of(Double.NaN,      "0xDeadFish")
				);
	}
}
