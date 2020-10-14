/*
    @author: Alina Khairullina, Ammon Kutay, Denys Myroniuk
    @version: 2018.06.05 
 */

@SuppressWarnings("serial")
public class BadFormattedInfixException extends Exception {
	public BadFormattedInfixException() {
		System.out.println("Missing parentheses");
	}
}
