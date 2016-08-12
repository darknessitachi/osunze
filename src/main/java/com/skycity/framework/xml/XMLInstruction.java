package com.skycity.framework.xml;

public class XMLInstruction extends XMLNode {
	String instruction;

	public XMLInstruction(XMLElement parent, String instruction) {
		this.instruction = instruction;
	}

	public void toString(String prefix, StringBuilder sb) {
		sb.append(prefix).append("<?").append(this.instruction).append(" ?>");
	}

	public String getText() {
		return "";
	}

	public int getType() {
		return 4;
	}
}