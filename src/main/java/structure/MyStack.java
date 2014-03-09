package structure;

/** A lax Stack implementation.
 */
@SuppressWarnings("unchecked")
// BEGIN main
public class MyStack<T> implements SimpleStack<T> {
	
	private int depth = 0;
	public static final int DEFAULT_INITIAL = 10;
	private T[] stack;
	
	public MyStack() {
		this(DEFAULT_INITIAL);
	}

	public MyStack(int howBig) {
		if (howBig <= 0) {
			throw new IllegalArgumentException(
			howBig + " must be positive, but was " + howBig);
		}
		stack = (T[])new Object[howBig];
	}
	
	@Override
	public boolean empty() {
		return depth == 0;
	}

	/** push - add an element onto the stack */
	@Override
	public void push(T obj) {
		// Could check capacity and expand
		stack[depth++] = obj;
	}

	/* pop - return and remove the top element */
	@Override
	public T pop() {
		--depth;
		T tmp = stack[depth];
		stack[depth] = null;
		return tmp;
	}
	
	/** peek - return the top element but don't remove it */
	@Override
	public T peek() {
		if (depth == 0) {
			return null;
		}
		return stack[depth-1];
	}
	
	public boolean hasNext() {
		return depth > 0;
	}

	public boolean hasRoom() {
		return depth < stack.length;
	}

	public int getStackDepth() {
		return depth;
	}
}
// END main
