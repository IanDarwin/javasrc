import java.util.Stack;

/** Stack class not damaged by meddling with its contents
 * using inherited Vector methods?
 */
void main() {
	var st = new Stack<Integer>();
	st.push(1); st.push(2); st.push(3);
	st.remove(1);
	st.add(2,42);
	st.add(99);

	while (!st.isEmpty()) {
		System.out.println(st.pop());
	}
	System.out.println("Done");
}
