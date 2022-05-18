package functional_med.data;

import functional_med.model.Reading;

/** An Acceptor accepts some elements from a Collection<Reading> */
@FunctionalInterface // Optional annotation, like @Override: for compile time check
public interface ReadingAcceptor {
	boolean test(Reading r);
}
