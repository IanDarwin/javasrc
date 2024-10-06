package di0;

import javax.inject.Named;

@Named("processor")
public class Process implements Processor {
	public int process(int x, int y) {
		return 2 * x + 3 * y;
	}
}
