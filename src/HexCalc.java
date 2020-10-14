/*
    @author: Alina Khairullina, Ammon Kutay, Denys Myroniuk
    @version: 2018.06.05 
 */

public class HexCalc extends CalcEngine {
	private Postfix test;

	public HexCalc() {
		super();
		test = new Postfix();
	}

	public void numberPressed(char number) {
		if (buildingDisplayValue) {
			// Incorporate this digit.
			displayValue += number;
		} else {
			// Start building a new number.
			displayValue += number;
			buildingDisplayValue = true;
		}
	}

	protected void calculateResult(boolean hexOn) {
		String pfix = null;
		int result = 0;
		if (hexOn) {
			pfix = test.infixToPostfix(convertHexToDec(getDisplayValue()));
			displayValue = "";
			result = test.evaluate(pfix);
			displayValue += result;
			displayValue = convertDecToHex(displayValue);
		} else {
			pfix = test.infixToPostfix(displayValue);
			result = test.evaluate(pfix);
			displayValue = "";
			displayValue += result + " ";
		}
	}

	public String convertHexToDec(String hex) {
		if (hex == null || hex == "")
			return "";
		String s[] = hex.trim().split(" "), decimal = "";
		for (String each : s) {
			if (test.isOperator(each)) {
				decimal += each + " ";
			} else {
				Integer number = Integer.parseInt(each, 16);
				decimal += number.toString() + " ";
			}
		}
		return decimal + " ";
	}

	public String convertDecToHex(String dec) {
		if (dec == null || dec == "")
			return "";
		String s[] = dec.trim().split(" "), hex = "";
		for (String each : s) {
			if (test.isOperator(each)) {
				hex += each + " ";
			} else {
				int number = Integer.parseInt(each);
				hex += Integer.toString(number, 16) + " ";
			}
		}
		return hex;
	}
}