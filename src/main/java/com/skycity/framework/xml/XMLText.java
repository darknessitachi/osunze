package com.skycity.framework.xml;

public final class XMLText extends XMLNode {
	String text;

	public XMLText(String text) {
		this.text = text;
	}

	public void toString(String prefix, StringBuilder sb) {
		if (this.text != null)
			encode(this.text, sb);
	}

	public String getText() {
		return this.text;
	}

	public int getType() {
		return 2;
	}
}