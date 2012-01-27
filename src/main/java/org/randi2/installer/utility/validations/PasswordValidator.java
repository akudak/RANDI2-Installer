package org.randi2.installer.utility.validations;

/* 
 * (c) 2008- RANDI2 Core Development Team
 * 
 * Uebernommen aus RANDI2
 *
 */
public class PasswordValidator {

	/** The Constant MAX_PASSWORD_LENGTH. */
	public final static int MAX_PASSWORD_LENGTH = 30;

	/** The Constant MIN_PASSWORD_LENGTH. */
	public final static int MIN_PASSWORD_LENGTH = 8;

	/** The Constant HASH_PASSWORD_LENGTH. */
	public final static int HASH_PASSWORD_LENGTH = 64;

	public boolean isValid(String password) {
		if (password == null)
			return false;

		if (password.length() <= MAX_PASSWORD_LENGTH
				&& password.length() >= MIN_PASSWORD_LENGTH) {
			String pLetter = ".*[A-Za-z].*";
			String pNumber = ".*[0-9].*";
			String pSpecialSign = ".*[-\\[\\]\\^,.#+;:_'*!\"§$%&/()=?|<>\\\\].*";
			return password.matches(pLetter) && password.matches(pNumber)
					&& password.matches(pSpecialSign);
		} else if (password.length() == HASH_PASSWORD_LENGTH)
			return true;

		return false;
	}
}
