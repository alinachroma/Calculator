/*
    @author: Alina Khairullina, Ammon Kutay, Denys Myroniuk
    @version: 2018.06.05 
 */


public class LinkedList<E> {
	/*
	 * Implementation specifically just for the stack. Since we do not need to add
	 * or remove from the middle of the list but only from the top, we implement the
	 * methods accordingly following the KISS method
	 */
	private Node<E> first = null;
	private int size;

	public void addFirst(E data) {
		Node<E> n = new Node<E>(data, null);
		n.setNext(first);
		first = n;
		size++;
	}

	public void removeTop() throws StackUnderflow {
		if (first == null || size < 1)
			throw new StackUnderflow();
		else {
			first = first.getNext();
			size--;
		}
	}

	public String toString() {
		Node<E> current = first;
		StringBuilder s = new StringBuilder();
		s.append("[");
		while (current != null) {
			s.append(current.getData());
			if (current.getNext() != null)
				s.append(", ");
			current = current.getNext();
		}
		s.append("]");
		return s.toString();
	}

	public boolean isEmpty() {
		return (first == null);
	}

	public E current() {
		return first.getData();
	}

	public int getSize() {
		return size;
	}
}
