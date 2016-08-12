package com.skycity.framework.i18n;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.BodyContent;
import javax.servlet.jsp.tagext.BodyTagSupport;

import com.skycity.framework.User;
import com.skycity.framework.utility.ObjectUtil;
import com.skycity.framework.utility.StringUtil;


public class LangTag extends BodyTagSupport {
    
    private static final long serialVersionUID = 1L;
    
    private String id;
    
    private String Default;
    
    private String language;
    
    
    public void setPageContext(PageContext pc) {
        super.setPageContext(pc);
        this.id = null;
        this.Default = null;
        this.language = null;
    }
    
    public int doStartTag() throws JspException {
        try {
        	Object obj = pageContext.getSession().getAttribute("_ZVING_USER");
        	String lang = "";
        	if(ObjectUtil.isNull(obj)){
    			lang = LangMapping.getInstance().getDefaultLanguage();
        	}else{
        		lang = ((User.UserData)obj).getLanguage();
    		}
            String str = LangMapping.get(lang,this.id);
            if (this.id != null) {
                if (StringUtil.isEmpty(str)) {
                    str = this.Default;
                }
                if (str == null) {
                    str = "@{" + this.id + "}";
                } 
                this.pageContext.getOut().write(str);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 2;
    }
    
    public int doAfterBody() throws JspException {
        if (this.language != null) {
            BodyContent body = getBodyContent();
            String content = body.getString().trim();
            try {
                getPreviousOut().write(content);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return 6;
    }
    
    public String getId() {
        return this.id;
    }
    
    public void setId(String var) {
        this.id = var;
    }
    
    public String getDefault() {
        return this.Default;
    }
    
    public void setDefault(String default1) {
        this.Default = default1;
    }
    
    public String getLanguage() {
        return this.language;
    }
    
    public void setLanguage(String language) {
        this.language = language;
    }
}
