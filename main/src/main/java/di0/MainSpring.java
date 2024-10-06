package di0;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MainSpring {
	public static void main(String[] args) {
        ApplicationContext ctx =
			new AnnotationConfigApplicationContext("di0");
        
        Processor processor = ctx.getBean(Processor.class);
        
		int answer = processor.process(2,3);
		System.out.printf("Process result from %s is %d\n",
			processor.getClass().getName(), answer);
	}
}
