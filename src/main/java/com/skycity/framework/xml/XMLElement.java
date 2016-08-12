package com.skycity.framework.xml;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.skycity.framework.utility.ObjectUtil;

public final class XMLElement extends XMLNode {
	LinkedHashMap<String, String> attributes = null;
	String QName;
	List<XMLNode> children;

	public XMLElement(String QName) {
		this.QName = QName;
	}

	public XMLElement addElement(String QName) {
		XMLElement node = new XMLElement(QName);
		node.setParent(this);
		return node;
	}

	public void addText(String text) {
		XMLText node = new XMLText(text);
		node.setParent(this);
	}

	public void addCDATA(String text) {
		XMLCDATA node = new XMLCDATA(text);
		node.setParent(this);
	}

	public XMLElement getParent() {
		return this.parent;
	}

	public void setParent(XMLElement parent) {
		this.parent = parent;
		if (parent.children == null) {
			parent.children = new ArrayList<XMLNode>();
		}
		if (!parent.children.contains(this))
			parent.children.add(this);
	}

	public void addComment(String comment) {
		new XMLComment(this, comment);
	}

	public void addInstruction(String instruction) {
		new XMLInstruction(this, instruction);
	}

	public LinkedHashMap<String, String> getAttributes() {
		if (this.attributes == null) {
			return null;
		}
		return this.attributes;
	}

	public LinkedHashMap<String, String> attributes() {
		if (this.attributes == null) {
			return null;
		}
		return this.attributes;
	}

	public String attributeValue(String attrName) {
		if (this.attributes == null) {
			return null;
		}
		return (String) this.attributes.get(attrName);
	}

	public void addAttribute(String attrName, String attrValue) {
		if (this.attributes == null) {
			this.attributes = new LinkedHashMap<String, String>(8);
		}
		this.attributes.put(attrName, attrValue);
	}

	public String getQName() {
		return this.QName;
	}

	public String getText() {
		if (this.children == null) {
			return "";
		}
		StringBuilder sb = new StringBuilder();
		for (XMLNode node : this.children) {
			if (ObjectUtil.in(new Object[] { Integer.valueOf(node.getType()),
					Integer.valueOf(2), Integer.valueOf(1) })) {
				sb.append(node.getText());
			}
		}
		return sb.toString();
	}

	public List<XMLElement> elements() {
		List<XMLElement> elements = new ArrayList<XMLElement>();
		if (this.children == null) {
			return elements;
		}
		for (XMLNode node : this.children) {
			if (node.getType() == 3) {
				elements.add((XMLElement) node);
			}
		}
		return elements;
	}

	public List<XMLElement> getElements() {
		return elements();
	}

	private List<XMLElement> elements(List<XMLElement> list, String prefix) {
		List<XMLElement> result = new ArrayList<XMLElement>();
		for (XMLElement node : list) {
			List<XMLElement> elems = node.elements();
			if (elems != null) {
				for (XMLElement node2 : elems) {
					if (("*".equals(prefix))
							|| (node2.getQName().equalsIgnoreCase(prefix)))
						result.add(node2);
				}
			}
		}
		return result;
	}

	public List<XMLElement> elements(String path) {
		String[] arr = path.split("\\.");
		List<XMLElement> list = new ArrayList<XMLElement>();
		list.add(this);
		for (int i = 0; i < arr.length; i++) {
			list = elements(list, arr[i]);
			if (list == null) {
				break;
			}
		}
		return list;
	}

	public String elementText(String path) {
		XMLElement child = element(path);
		if (child == null) {
			return "";
		}
		return child.getText();
	}

	public XMLElement element(String path) {
		List<XMLElement> nodes = elements(path);
		return (nodes == null) || (nodes.size() == 0) ? null
				: (XMLElement) nodes.get(0);
	}

	public Object elementAttribute(String path, String attrName) {
		List<XMLElement> nodes = elements(path);
		if (nodes.size() == 0) {
			return null;
		}
		return ((XMLElement) nodes.get(0)).getAttributes().get(attrName);
	}

	public List<XMLElement> elementsByAttribute(String attrName,
			String attrValue) {
		return elementsByAttribute(null, attrName, attrValue);
	}

	public List<XMLElement> elementsByAttribute(String path, String attrName,
			String attrValue) {
		List<XMLElement> nodes = elements(path);
		if ((nodes == null) || (nodes.size() == 0)) {
			return null;
		}
		List<XMLElement> result = new ArrayList<XMLElement>();
		for (XMLElement n : nodes) {
			if (attrValue == null) {
				if (n.getAttributes().get(attrName) == null)
					result.add(n);
			} else if (attrValue.equals(n.getAttributes().get(attrName))) {
				result.add(n);
			}
		}
		return result;
	}

	public String toString() {
		StringBuilder sb = new StringBuilder();
		toString("", sb);
		return sb.toString();
	}

	public void toString(String prefix, StringBuilder sb) {
		sb.append(prefix);
		sb.append("<");
		sb.append(this.QName);
		if (this.attributes != null) {
			for (Map.Entry<String,String> entry : this.attributes.entrySet()) {
				sb.append(" ");
				sb.append((String) entry.getKey());
				sb.append("=\"");
				encode((String) entry.getValue(), sb);
				sb.append("\"");
			}
		}
		if ((this.children == null) || (this.children.size() == 0)) {
			sb.append(" />");
		} else {
			sb.append(">");
			if ((this.children.size() == 1)
					&& (((XMLNode) this.children.get(0)).getType() == 2)) {
				((XMLNode) this.children.get(0)).toString("", sb);
				sb.append("</");
				sb.append(this.QName);
				sb.append(">");
			} else {
				for (XMLNode child : this.children) {
					sb.append("\n");
					child.toString(prefix + "\t", sb);
				}
				sb.append("\n");
				sb.append(prefix);
				sb.append("</");
				sb.append(this.QName);
				sb.append(">");
			}
		}
	}

	public int getType() {
		return 3;
	}
}