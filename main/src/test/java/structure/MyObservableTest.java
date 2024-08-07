package structure;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.Test;

class MyObservableTest {

	@Test
	void observableNonStrict() {
		MyObservable subj1 = new MyObservable();
		Observer obs1 = mock(Observer.class);
		subj1.addObserver(obs1);
		subj1.notifyObservers();
		verify(obs1, times(1)).update(subj1, null);
		subj1.notifyObservers();
		// This will be times(2) since we re-used the mock
		verify(obs1, times(2)).update(subj1, null);
	}
}
