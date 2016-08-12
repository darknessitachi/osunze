package com.skycity.framework.tag;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.BodyTagSupport;

import org.apache.commons.lang.StringUtils;

import com.skycity.framework.Config;

public class AttrTag extends BodyTagSupport {

	private static final long serialVersionUID = 1L;

	private String type;
	private String value;
	
	public int doStartTag() throws JspException {
		try {
			if(StringUtils.equals("SystemInfo", this.type)){
				this.pageContext.getOut().write(Config.getString(this.value));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 2;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

}
