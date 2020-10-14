/*
    @author: Alina Khairullina, Ammon Kutay, Denys Myroniuk
    @version: 2018.06.05 
 */

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class HexUserInterface extends UserInterface {
	protected HexCalc calc;
	private boolean hexOn = false;

	public HexUserInterface(HexCalc engine) {
		super(engine);
		calc = engine;
		showingAuthor = true;
		makeFrame();
		frame.setVisible(true);

	}

	private void makeFrame() {

		JPanel contentPane = (JPanel) frame.getContentPane();

		JPanel buttonPanel2 = new JPanel(new GridLayout(1, 6));
		addButton(buttonPanel2, "A");
		addButton(buttonPanel2, "B");
		addButton(buttonPanel2, "C");
		addButton(buttonPanel2, "D");
		addButton(buttonPanel2, "E");
		addButton(buttonPanel2, "F");
		buttonPanel2.setVisible(false);

		JPanel newOperationsPanel1 = new JPanel(new GridLayout(6, 6));

		addButton(newOperationsPanel1, "/");
		addButton(newOperationsPanel1, "*");
		addButton(newOperationsPanel1, "^");
		JCheckBox checkbox = new JCheckBox("Hexadecimal");

		checkbox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (buttonPanel2.isVisible()) {
					buttonPanel2.setVisible(false);
					hexOn = false;
					convertDisplay();
				} else {
					buttonPanel2.setVisible(true);
					hexOn = true;
					convertDisplay();
				}
			}
		});
		newOperationsPanel1.add(checkbox);
		checkbox.addActionListener(this);
		newOperationsPanel1.add(new JLabel(" "));

		contentPane.add(newOperationsPanel1, BorderLayout.EAST);
		contentPane.add(buttonPanel2, BorderLayout.SOUTH);
		frame.pack();
	}

	public void actionPerformed(ActionEvent event) {
		super.actionPerformed(event);
		String command = event.getActionCommand();
		if (command.equals("A") || command.equals("B") || command.equals("C") || command.equals("D")
				|| command.equals("E") || command.equals("F") || command.equals("+") || command.equals("-")
				|| command.equals("*") || command.equals("/") || command.equals("^")) {
			calc.displayValue += command + " ";
		} else if (command.equals("=")) {
			calc.calculateResult(hexOn);
		} else if (command.equals("CE")) {
			calc.clear();
		} else if (command.equals("?")) {
			showInfo();
		}

		// else unknown command.
		redisplay();
	}

	private void convertDisplay() {
		if (hexOn) {
			calc.setDisplayValue(calc.convertDecToHex(calc.displayValue));
			display.setText(calc.getDisplayValue());
		} else {
			calc.setDisplayValue(calc.convertHexToDec(calc.displayValue));
			display.setText(calc.displayValue);
		}
	}

	protected void redisplay() {
		display.setText(calc.getDisplayValue());
	}
}