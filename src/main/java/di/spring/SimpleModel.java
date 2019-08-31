package di.spring;

import org.springframework.stereotype.Component;

// tag::main[]
@Component("myModel")
public class SimpleModel implements Model {

	@Override
	public String getMessage() {
		return "This is some simple model data";
	}
}
// end::main[]
