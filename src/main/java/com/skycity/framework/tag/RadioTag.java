package com.skycity.framework.tag;

import javax.servlet.jsp.PageContext;


public class RadioTag extends CheckboxTag {

	private static final long serialVersionUID = 5401430414457722700L;

	public void setPageContext(PageContext pc) {
        super.setPageContext(pc);
        this.type = "radio";
    }

}
