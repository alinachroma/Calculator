/*
    @author: Alina Khairullina, Ammon Kutay, Denys Myroniuk
    @version: 2018.06.05 
 */


public class StackAsList<E> {
	private LinkedList<E> stack;

	public StackAsList() {
		this.Empty();
	}

	public void Empty() {
		stack = new LinkedList<E>();
	}

	public boolean isEmpty() {
		return stack.isEmpty();
	}

	public E pop() throws StackUnderflow {
		E temp;
		if (stack.isEmpty()) {
			throw new StackUnderflow();
		} else {
// chop off the first one, so make sure we are at the top
			temp = stack.current();
			stack.removeTop();
		}
		return temp;
	}

	public void push(E c) {
// All pushing is done on the front
		stack.addFirst(c);
	}

	public E top() throws StackUnderflow {
// stack.reset();
		return stack.current();
	}

	public String toString() {
		return stack.toString();
	}

	public int getSize() {
		return stack.getSize();
	}

	public StackAsList(String s) {
		StackAsList<String> temp = new StackAsList<>();
		String[] n = s.trim().split(" ");
		for (String each : n) {
			temp.push(each);
		}
	}
}