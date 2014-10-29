package functional;

import java.util.List;

public interface MyDemo {
	void process();
	default void estimate(List<MyTask> list) {
		list.forEach(le -> {/* do something */});
	}
}

class MyTask {
	
}