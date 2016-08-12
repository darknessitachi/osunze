package com.skycity.framework.xml;

import java.io.File;
import java.io.InputStream;
import java.util.List;

import com.skycity.framework.utility.FileUtil;

public class XMLMultiLoader {
	private XMLElement root = new XMLElement("_ROOT");

	public void load(String path) throws XMLParseException {
		File f = new File(path);
		load(f);
	}

	public void load(File f) throws XMLParseException {
		if (f.isFile()) {
			loadOneFile(f);
		} else {
			File[] fs = f.listFiles();
			for (int i = 0; i < fs.length; i++) {
				f = fs[i];
				if ((f.isFile())
						&& ((f.getName().toLowerCase().endsWith(".xml")) || (f
								.getName().toLowerCase().endsWith(".plugin"))))
					loadOneFile(f);
			}
		}
	}

	public void load(InputStream is) throws XMLParseException {
		loadOneFile(is);
	}

	private void loadOneFile(File f) throws XMLParseException {
		loadOneFile(FileUtil.readText(f));
	}

	public void clear() {
		this.root = new XMLElement("_ROOT");
	}

	private void loadOneFile(InputStream is) throws XMLParseException {
		XMLParser parser = new XMLParser(is);
		try {
			parser.parse();
			XMLElement singleRoot = parser.getDocument().getRoot();
			singleRoot.setParent(this.root);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void loadOneFile(String xml) throws XMLParseException {
		XMLParser parser = new XMLParser(xml);
		parser.parse();
		XMLElement singleRoot = parser.getDocument().getRoot();
		singleRoot.setParent(this.root);
	}

	public XMLElement getRoot() {
		return this.root;
	}

	public XMLElement getElement(String path, String attrName, String attrValue) {
		List<XMLElement> list = this.root.elementsByAttribute(path, attrName, attrValue);
		if ((list == null) || (list.size() == 0)) {
			return null;
		}
		return (XMLElement) list.get(0);
	}

	public List<XMLElement> getElements(String path) {
		return this.root.elements(path);
	}
}