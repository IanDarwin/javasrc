package structure;

import org.junit.Test;

import static org.mockito.Mockito.*;

import java.util.*;

public class MyObservableTest {

	@Test
	public void testObservableNonStrict() {
		MyObservable subj1 = new MyObservable(false);
		Observer obs1 = mock(Observer.class);
		subj1.addObserver(obs1);
		subj1.notifyObservers();
		verify(obs1, times(1)).update(subj1, null);
		subj1.setChanged(true);
		subj1.notifyObservers();
		// This will be times(2) since we re-used the mock
		verify(obs1, times(2)).update(subj1, null);
	}

	@Test
	public void testObservableStrict() {
		MyObservable subj1 = new MyObservable();
		Observer obs1 = mock(Observer.class);
		subj1.addObserver(obs1);
		subj1.notifyObservers();
		verify(obs1, never()).update(subj1, null);
		subj1.setChanged(true);
		subj1.notifyObservers();
		verify(obs1, times(1)).update(subj1, null);
	}
}
