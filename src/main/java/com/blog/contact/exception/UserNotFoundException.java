package com.blog.contact.exception;

public class UserNotFoundException extends RuntimeException{
	/**
	 * Custom exception when user not found
	 */
	private static final long serialVersionUID = 1L;

	public UserNotFoundException(String identifier) {
		super("could not find user having '" + identifier + "'.");
	}
}
