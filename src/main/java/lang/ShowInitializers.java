package lang;

/** Just show use of initializers, and which ones need to be initialized. */
public class ShowInitializers {
		int quantity;		// defaults to zero
		int value = 0;		// better style?
		void setValue() {
			int rate;		// un-initialized by default
			doSomethingWith(rate);	// EXPECT COMPILE ERROR
		}

		/** This method is not shown in the course notes.
		 * It doesn't do anything with its input. 
		 */
		public void doSomethingWith(int r) {
		}
}
