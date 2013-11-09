package di.spring;

import org.springframework.stereotype.Component;

@Component("myModel")
public class SimpleModel implements Model {

	@Override
	public String getMessage() {
		return "hardcoded model data";
	}

}
