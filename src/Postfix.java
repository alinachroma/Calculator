/*
    @author: Alina Khairullina, Ammon Kutay, Denys Myroniuk
    @version: 2018.06.05 
 */

public class Postfix {
	// stack used for evaluating postfix
	private StackAsList<Integer> eval;
	// stack used for evaluating postfix and converting infix to postfix
	private StackAsList<String> pfix;
	// remembers that a previous operator must still be added
	private boolean missingOperator;

	// default constructor with default stack
	public Postfix() {
		this.eval = new StackAsList<>();
		this.pfix = new StackAsList<>();
		this.missingOperator = false;
	}

	public int evaluate(String pfx) {
		try {
			// remove trailing whitespace and split at space
			String[] postfix = pfx.trim().split(" ");
			// evaluating postfix
			for (String c : postfix) {
	// on operator pop the last two values and push the result to
	// the stack
				if (isOperator(c)) {
					int second = eval.pop();
					int first = eval.pop();
					eval.push(applyOperator(c, first, second));
				} else {
					// on numbers just push to the stack
					eval.push(Integer.parseInt(c));
				}
			}
			// at the end there should be only one element - the final result
			return eval.pop();
		} catch (StackUnderflow e) {
			System.out.println("Missing operands or to many operators in postfix notation.");
		}
		return 0;
	}

	public String infixToPostfix(String ifx) {
		try {
			// remove trailing whitespace and split at space
			String[] infix = ifx.trim().split(" ");
			String postfix = "";
			// going through all characters
			for (String c : infix) {
				// numbers are instantly added to the postfix string
				if (isNumber(c)) {
					postfix = postfix + c + " ";
				}
				// on open parentheses just push to the stack
				if (c.equals("(")) {
					pfix.push(c);
					// on closed parentheses add last operators to postfix
				} else if (c.equals(")")) {
					while (!pfix.isEmpty() && !pfix.top().equals("(")) {
						postfix = postfix + pfix.pop() + " ";
					}
	// remove the open parenthesis
	// if this throws an error or creates an error there were too many closed
	// parentheses
					if (pfix.isEmpty()) {
						throw new BadFormattedInfixException();
					}
					pfix.pop();
				}
				// operators are more complex
				if (isOperator(c)) {
	// operator is added when the previous operator has a higher
	// precedence than the current one
	// OR when the operator is right associative ("-","/") and
	// equal to the previous one
					while (!pfix.isEmpty() && !pfix.top().equals("(")
							&& (getPrecedence(c) == Precedence.LOWER || (getPrecedence(c) == Precedence.EQUAL
									&& ((isRightAssociative(pfix.top()) && isRightAssociative(c))
											|| (isRightAssociative(pfix.top()) && !isRightAssociative(c)))))) {
						postfix = postfix + pfix.pop() + " ";
	// checking if there was an edge case with right)
	// associative operator before a higher precedence
	// operator
						if (missingOperator) {
							postfix = postfix + pfix.pop() + " ";
						}
					}
	// when the operator has higher precedence than a right
	// associative, the operator will be added after the
	// operator
	// 3 - 4 ^ 5 + 6 => 3 4 5 ^ - 6 +
					if (!pfix.isEmpty() && getPrecedence(c) == Precedence.HIGHER
							&& isRightAssociative((String) pfix.top())) {
						missingOperator = true;
					}
					// push operator to the stack
					pfix.push(c);
				}
			}
			// add the operators left in the stack
			while (!pfix.isEmpty()) {
				postfix = postfix + pfix.pop() + " ";
			}
			// remove trailing whitespace and return value
			return postfix.trim();
		} catch (StackUnderflow | BadFormattedInfixException e) {
			System.out.println("Badly formatted input string.");
		}
		return "";
	}

	private enum Precedence {
		LOWER, EQUAL, HIGHER;
	}

	// right associative operators are not commutative which means the order
	// matters (3 - 5 != 5 - 3) else the results will be different
	// e. g. 15 / 5 / 3 <=> ((15 / 5) / 3)
	// wrong postfix: 15 5 3 / / => (15 / (5 / 3)) == 1.6
	// right postfix: 15 5 / 3 / => ((15 / 5) / 3) == 1
	private boolean isRightAssociative(String ops) {
		return ops.equals("-") || ops.equals("/");
	}

	//compares the precedence of the operator with the top one on the stack
	private Precedence getPrecedence(String operator) {
		try {
			String previousOperator = pfix.top();
			switch (operator) {
			case "+":
				if (previousOperator.equals("-") || previousOperator.equals("+")) {
					return Precedence.EQUAL;
				}
				break;
			case "-":
				if (previousOperator.equals("+") || previousOperator.equals("-")) {
					return Precedence.EQUAL;
				}
				break;
			case "*":
				if (previousOperator.equals("+") || previousOperator.equals("-")) {
					return Precedence.HIGHER;
				}
				if (previousOperator.equals("/") || previousOperator.equals("*")) {
					return Precedence.EQUAL;
				}
				break;
			case "/":
				if (previousOperator.equals("+") || previousOperator.equals("-")) {
					return Precedence.HIGHER;
				}
				if (previousOperator.equals("*") || previousOperator.equals("/")) {
					return Precedence.EQUAL;
				}
				break;
			case "^":
				if (previousOperator.equals("^")) {
					return Precedence.EQUAL;
				}
				return Precedence.HIGHER;
			default:
				System.out.println("Unknown Operator");
			}
		} catch (StackUnderflow e) {
			System.out.println("Empty Stack.");
		}
		return Precedence.LOWER;
	}

	// returns whether the input is an operator or not
	public boolean isOperator(String input) {
		return input.equals("+") || input.equals("-") || input.equals("*") || input.equals("/") || input.equals("^");
	}

	// returns whether the number is an operator or not
	// parseInt throws an exception whenever the input string is not a number
	private boolean isNumber(String n) {
		try {
			Integer.parseInt(n);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}

	// applies the operator to the two variables and returns the value
	// only binary operator!
	private int applyOperator(String operator, int first, int second) {
		switch (operator) {
		case "+":
			return first + second;
		case "-":
			return first - second;
		case "*":
			return first * second;
		case "/":
			return first / second;
		case "^":
			return (int) Math.pow(first, second);
		default:
			System.out.println("Unknown Operator");
			return 0;
		}
	}
}