import java.util.regex.*;

/// A simple example of using Regex as an alternative to StringTokenizer
///
void main() {
	process("471,472,570");
	process("471 472 570");
	process("Course 471 - Course 472 - Course 570");
}

void process(String inputString) {
	Matcher tokenizer = Pattern.compile("\\d+").matcher(inputString);
	while (tokenizer.find( )) {
        String courseString = tokenizer.group(0);
        var courseNumber = Integer.parseInt(courseString);
		System.out.print(courseNumber);
		System.out.print(' ');
	}
	System.out.println();
}
