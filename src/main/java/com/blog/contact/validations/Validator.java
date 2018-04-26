package com.blog.contact.validations;

/*
 * Validator class to do basic validation on attributes
 * ideally, spring validator should be used.
 * */
public class Validator {
	/*
	 * This utility class does not need public constructor
	 */
	private Validator() {
	}

	/*
	 * Validates phone number only if its not null, otherwise return true, so that
	 * other not-null valid filter can be utilized properly
	 * 
	 */
	public static boolean validatePhoneNumber(String phoneNumber) {
		boolean isValid = true;
		if (phoneNumber != null && !(phoneNumber.length() == 10))
			isValid = false;
		return isValid;
	}
}
