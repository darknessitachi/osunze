package com.skycity.framework.xml;

public class XMLWriteException extends Exception {
	private static final long serialVersionUID = 1L;

	public XMLWriteException(String message) {
		super(message);
	}

	public XMLWriteException(Throwable t) {
		super(t);
	}

	public XMLWriteException(String message, Throwable cause) {
		super(message, cause);
	}
}