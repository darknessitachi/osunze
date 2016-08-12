package com.skycity.framework.tag;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.BodyContent;
import javax.servlet.jsp.tagext.BodyTagSupport;


public class ToolBarTag extends BodyTagSupport {
    
    private static final long serialVersionUID = 1L;
    
    private String theme;
    
    public void setPageContext(PageContext pc) {
        super.setPageContext(pc);
        this.theme = null;
        this.id = null;
    }
    
    public int doStartTag() throws JspException {
        try {
            this.pageContext.getOut().print("<div class=\"z-toolbar");
            if ("flat".equals(this.theme)) {
                this.pageContext.getOut().print(" z-toolbar-flat");
            }
            this.pageContext.getOut().print("\"");
            this.pageContext.getOut().print(">");
            this.pageContext.getOut().print("<div class=\"z-toolbar-ct\">");
            this.pageContext.getOut().print("<div class=\"z-toolbar-overflow\">");
            this.pageContext.getOut().print("<div class=\"z-toolbar-nowrap\">");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return 2;
    }
    
    public int doAfterBody() throws JspException {
        BodyContent body = getBodyContent();
        String content = body.getString().trim();
        try {
            getPreviousOut().write(content);
            getPreviousOut().write("</div></div></div></div>");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return 6;
    }
    
    public String getTheme() {
        return this.theme;
    }
    
    public void setTheme(String theme) {
        this.theme = theme;
    }
    
}
