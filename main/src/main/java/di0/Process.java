package di0;

import org.springframework.beans.factory.annotation.Autowired;

import javax.inject.Named;

// tag::main[]
@Named("processor")
public class Process implements Processor {

	@Autowired
	private Reporter reporter;

	public void process(int x, int y) {
		var result = 2 * x + 3 * y;
		reporter.report(result);
	}
}
// end::main[]
