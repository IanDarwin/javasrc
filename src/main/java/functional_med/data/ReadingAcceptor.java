package functional_med.data;

import functional_med.model.Reading;

/** An Acceptor accepts some elements from a Collection */
@FunctionalInterface // Optional, like @Override: just for compile time check
public interface ReadingAcceptor {
	boolean test(Reading r);
}
