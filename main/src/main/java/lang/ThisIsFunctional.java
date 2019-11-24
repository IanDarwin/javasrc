package lang;

@FunctionalInterface
public interface ThisIsFunctional {
	int compute(int x);
	// If you uncomment the following method definition, the interface
	// will not compile with @FunctionalInterface on, as it ceases to
	// be functional when it has two methods.
	// int recompute(int x);
}