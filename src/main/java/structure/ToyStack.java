package structure;

/** Toy Stack. 
 * @version $Id$
 */
public class ToyStack {

	/** The maximum stack depth */
	protected int MAX_DEPTH = 10;
	/** The current stack depth */
	protected int depth = 0;
	/* The actual stack */
	protected int[] stack = new int[MAX_DEPTH];

	/* Implement a toy stack version of push */
	protected void push(int n) {
		stack[depth++] = n;
	}
	/* Implement a toy stack version of pop */
	protected int pop() {
		return stack[--depth];
	}
	/* Implement a toy stack version of peek */
	protected int peek() {
		return stack[depth-1];
	}
}
