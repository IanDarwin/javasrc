package structure;

// BEGIN main
/** Toy Stack, converted to SimpleStack interface. 
 */
public class ToyStack2 implements SimpleStack<Integer> {

	/** The maximum stack depth */
	protected int MAX_DEPTH = 10;
	/** The current stack depth */
	protected int depth = 0;
	/* The actual stack */
	protected int[] stack = new int[MAX_DEPTH];

	@Override
	public boolean empty() {
		return depth == 0;
	}
	
	@Override
	public void push(Integer n) {
		stack[depth++] = n;
	}
	
	@Override
	public Integer pop() {
		return stack[--depth];
	}
	
	@Override
	public Integer peek() {
		return stack[depth-1];
	}
}
// END main
