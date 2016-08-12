package com.skycity.framework.xml;

public class XMLComment extends XMLNode {
	String comment;

	public XMLComment(XMLElement parent, String comment) {
		this.comment = comment;
	}

	public void toString(String prefix, StringBuilder sb) {
		sb.append(prefix).append("<!--").append(this.comment).append("-->");
	}

	public String getText() {
		return "";
	}

	public int getType() {
		return 5;
	}
}