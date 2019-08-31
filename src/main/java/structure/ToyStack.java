package structure;

/** Toy Stack. 
 */
// tag::main[]
public class ToyStack {

	/** The maximum stack depth */
	protected int MAX_DEPTH = 10;
	/** The current stack depth */
	protected int depth = 0;
	/* The actual stack */
	protected int[] stack = new int[MAX_DEPTH];

	/** push - add an element onto the stack */
	protected void push(int n) {
		stack[depth++] = n;
	}
	/** pop - return and remove the top element */
	protected int pop() {
		return stack[--depth];
	}
	/** peek - return the top element but don't remove it */
	protected int peek() {
		return stack[depth-1];
	}
}
// end::main[]
