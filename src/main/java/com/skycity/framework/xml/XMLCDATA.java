package com.skycity.framework.xml;

public final class XMLCDATA extends XMLNode {
	String text;

	public XMLCDATA(String text) {
		this.text = text;
	}

	public void toString(String prefix, StringBuilder sb) {
		sb.append(prefix).append("<![CDATA[")
				.append(this.text == null ? "" : this.text).append("]]>");
	}

	public String getText() {
		return this.text;
	}

	public int getType() {
		return 1;
	}
}