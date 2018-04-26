package com.blog.contact.exception;

public class InvalidFilterException extends RuntimeException{
	/**
	 * Custom exception when invalid value
	 */
	private static final long serialVersionUID = 1L;

	public InvalidFilterException(String identifier) {
		super("Invalid value '" + identifier + "'.");
	}
}
