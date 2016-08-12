package com.skycity.framework.xml;

import java.io.File;
import java.io.OutputStream;
import java.io.Writer;

import com.skycity.framework.utility.FileUtil;

public class XMLWriter {
	public static void writeTo(XMLDocument doc, File f) {
		FileUtil.writeText(f.getAbsolutePath(), doc.toString(),doc.getEncoding());
	}

	public static void writeTo(XMLDocument doc, OutputStream os) throws XMLWriteException {
		try {
			os.write(doc.toString().getBytes(doc.getEncoding()));
		} catch (Exception e) {
			throw new XMLWriteException(e);
		}
	}

	public static void writeTo(XMLDocument doc, Writer writer) throws XMLWriteException {
		try {
			writer.write(doc.toString());
		} catch (Exception e) {
			throw new XMLWriteException(e);
		}
	}
}