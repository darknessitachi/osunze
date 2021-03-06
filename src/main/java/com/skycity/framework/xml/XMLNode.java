package com.skycity.framework.xml;

import java.util.ArrayList;

import com.skycity.framework.utility.StringUtil;

public abstract class XMLNode {
	public static final int CDATA = 1;
	public static final int TEXT = 2;
	public static final int ELEMENT = 3;
	public static final int INSTRUCTION = 4;
	public static final int COMMENT = 5;
	XMLElement parent;

	public abstract void toString(String paramString,
			StringBuilder paramFastStringBuilder);

	public abstract String getText();

	public abstract int getType();

	public void setParent(XMLElement parent) {
		if (this.parent != null) {
			this.parent.children.remove(parent);
		} else {
			this.parent = parent;
			if (this.parent.children == null) {
				this.parent.children = new ArrayList<XMLNode>(4);
			}
			this.parent.children.add(this);
		}
	}

	public void encode(String value, StringBuilder sb) {
		if (StringUtil.isEmpty(value)) {
			return;
		}

		for (int i = 0; i < value.length(); i++) {
			char c = value.charAt(i);
			if (c == '<')
				sb.append("&lt;");
			else if (c == '>')
				sb.append("&gt;");
			else if (c == '"')
				sb.append("&quot;");
			else if (c == '\'')
				sb.append("&apos;");
			else if (c == '&')
				sb.append("&amp;");
			else
				sb.append(c);
		}
	}

	public XMLElement getParent(XMLElement parent) {
		return parent;
	}

	public String getXML() {
		return toString();
	}

	public String toString() {
		StringBuilder sb = new StringBuilder();
		// toString("", sb);
		return sb.toString();
	}
}