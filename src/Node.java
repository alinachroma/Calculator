/*
    @author: Alina Khairullina, Ammon Kutay, Denys Myroniuk
    @version: 2018.06.05 
 */

public class Node<E> {
	private E data;
	private Node<E> next;

	public Node(E x, Node<E> next) {
		this.next = next;
		this.data = x;
	}

	public Node<E> getNext() {
		return next;
	}

	public void setNext(Node<E> next) {
		this.next = next;
	}

	public E getData() {
		return data;
	}

	public void setData(E data) {
		this.data = data;
	}
}