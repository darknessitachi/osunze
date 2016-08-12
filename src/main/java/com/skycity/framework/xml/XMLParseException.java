package com.skycity.framework.xml;

public class XMLParseException extends Exception {
	private static final long serialVersionUID = 1L;

	public XMLParseException(String message) {
		super(message);
	}

	public XMLParseException(Throwable t) {
		super(t);
	}

	public XMLParseException(String message, Throwable cause) {
		super(message, cause);
	}
}