package org.randi2.installer.utility.validations;

/* 
 * (c) 2008- RANDI2 Core Development Team
 * 
 * Uebernommen aus RANDI2
 *
 */
public class TelephoneNumberValidator {

	public boolean isValid(String number) {
		if (number == null || number.trim().equals("")) {
			return true;
		}
		number = number.trim();

		String[] digits = number.split("[-/() \t.]");
		StringBuffer buf = new StringBuffer();

		for (int i = 0; i < digits.length; i++) {
			buf.append(digits[i].trim());
		}
		number = buf.toString();
		if (number.charAt(0) == '+') {
			number = number.substring(1, number.length());
		}
		if (!number.matches("^\\d+$")) {
			return false;
		}

		return true;
	}
}
