package di.spring;

import javax.annotation.Resource;

public class SpringModel implements Model {

	private String message = "Hello from Spring";

	@Override
	public String getMessage() {
		return message;
	}

	@Resource(name="message")
	public void setMessage(String message) {
		this.message = message;
	}

}
