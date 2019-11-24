package di.spring;

import javax.inject.Named;

import di.Model;

// tag::main[]
@Named("myModel")
public class SimpleModel implements Model {

	@Override
	public String getMessage() {
		return "This is some simple model data";
	}
}
// end::main[]
