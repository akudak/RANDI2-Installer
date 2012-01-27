package org.randi2.installer.utility.validations;

/* 
 * (c) 2008- RANDI2 Core Development Team
 * 
 * Uebernommen aus RANDI2
 *
 */
public class EMailRANDI2Validator {

	public boolean isValid(String mail) {
		if (mail == null)
			return false;
		else {
			return mail
					.matches("[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@([A-Za-z0-9-]+(\\.)?)+\\.([a-zA-Z]){2,4}");
		}
	}
}
