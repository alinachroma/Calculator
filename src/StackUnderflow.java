/*
    @author: Alina Khairullina, Ammon Kutay, Denys Myroniuk
    @version: 2018.06.05 
 */


@SuppressWarnings("serial")
public class StackUnderflow extends Exception {
	public StackUnderflow() {
		System.out.println("Stack is already empty");
	}
}