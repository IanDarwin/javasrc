package di.spring;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ControllerTightlyCoupled {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ApplicationContext ctx = new AnnotationConfigApplicationContext( "di.spring");
		View v = ctx.getBean("messageRenderer", View.class);
		v.displayMessage();
	}
}
