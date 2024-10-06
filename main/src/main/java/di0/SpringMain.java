package di0;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class SpringMain {
	public static void main(String[] args) {
        ApplicationContext ctx =
			new AnnotationConfigApplicationContext("di0");
        
        Processor processor = ctx.getBean(Processor.class);
        
		processor.process(2,3);
	}
}
