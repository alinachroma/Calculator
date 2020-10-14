/**
 * The main part of the calculator doing the calculations.
 *
 * @author David J. Barnes and Michael Kolling
 * @version 2008.03.30
 */
public class CalcEngine {
	// The calculator's state is maintained in three fields:
	// buildingDisplayValue, haveLeftOperand, and lastOperator.

	// Are we already building a value in the display, or will the
	// next digit be the first of a new one?
	protected boolean buildingDisplayValue;
	// Has a left operand already been entered (or calculated)?
	protected boolean haveLeftOperand;
	// The most recent operator that was entered.
	protected char lastOperator;
	// The current value (to be) shown in the display.
	protected String displayValue = null;

	protected void setDisplayValue(String displayValue) {
		this.displayValue = displayValue;
	}

// The value of an existing left operand.
	protected int leftOperand;

	/**
	 * Create a CalcEngine.
	 */
	public CalcEngine() {
		clear();
	}

	/**
	 * @return The value that should currently be displayed on the calculator
	 *         display.
	 */
	public String getDisplayValue() {
		return displayValue;
	}

	/**
	 * The 'C' (clear) button was pressed. Reset everything to a starting state.
	 */
	public void clear() {
		displayValue = "";
	}

	/**
	 * @return The title of this calculation engine.
	 */
	public String getTitle() {
		return "Java Calculator";
	}

	/**
	 * @return The author of this engine.
	 */
	public String getAuthor() {
		return "David J. Barnes and Michael Kolling";
	}

	/**
	 * @return The version number of this engine.
	 */
	public String getVersion() {
		return "Version 1.0";
	}

	/**
	 * Report an error in the sequence of keys that was pressed.
	 */
	protected void keySequenceError() {
		System.out.println("A key sequence error has occurred.");
		// Reset everything.
		clear();
	}
}