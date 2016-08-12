package com.skycity.framework.tag;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.BodyTagSupport;

import com.skycity.framework.utility.StringUtil;


public class ButtonTag extends BodyTagSupport {
    
    private static final long serialVersionUID = 1L;
    
    private String id;
    
    private String name;
    
    private String onClick;
    
    private String href;
    
    private String target;
    
    private String theme;
    
    private boolean disabled;
    
    public static final Pattern PImg = Pattern.compile("<img .*?src\\=.*?>", 34);
    
    public void setPageContext(PageContext pc) {
        super.setPageContext(pc);
        this.id = null;
        this.name = null;
        this.onClick = null;
        this.href = null;
        this.target = null;
        this.theme = null;
        this.disabled = false;
    }
    
    public int doAfterBody() throws JspException {
        String content = getBodyContent().getString();
        try {
            Matcher matcher = PImg.matcher(content);
            String img = null;
            String text = null;
            if (matcher.find()) {
                img = content.substring(matcher.start(), matcher.end());
                text = content.substring(matcher.end());
            }
            getPreviousOut().print(getHtml(this.id, this.name, this.theme,  this.onClick, this.href, this.target, img,text, this.disabled));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 6;
    }

    public static String getHtml(String id, String name, String theme, String onclick, String href,
        String target, String img, String text, boolean disabled) {
        StringBuilder sb = new StringBuilder();
        sb.append("<a href='").append(StringUtil.isNotEmpty(href) ? href : "javascript:void(0);").append("'");
        if (StringUtil.isNotEmpty(target)) {
            sb.append(" target='" + target + "'");
        }
        sb.append(" id='" + id + "'");
        if (StringUtil.isNotEmpty(name)) {
            sb.append(" name='" + name + "'");
        }
        sb.append(" class='z-btn");
        if (StringUtil.isNotEmpty(theme)) {
            sb.append(" z-btn-" + theme);
        }
        if (disabled) {
            sb.append(" z-btn-disabled");
        }
        sb.append("'");
        if (onclick != null) {
            if (disabled) {
                sb.append(" _onclick_bak=\"").append(onclick);
            } else {
                sb.append(" onclick=\"").append(onclick);
            }
        }
        sb.append("\">");
        sb.append(img);
        sb.append("<b>").append(text).append("</b>");
        sb.append("</a>");
        return sb.toString();
    }
    
    public String getId() {
        return this.id;
    }
    
    public void setId(String id) {
        this.id = id;
    }
    
    public String getOnClick() {
        return this.onClick;
    }
    
    public void setOnClick(String onClick) {
        this.onClick = onClick;
    }
    
    public String getHref() {
        return this.href;
    }
    
    public void setHref(String href) {
        this.href = href;
    }
    
    public String getTarget() {
        return this.target;
    }
    
    public void setTarget(String target) {
        this.target = target;
    }
    
    public boolean isDisabled() {
        return this.disabled;
    }
    
    public void setDisabled(boolean disabled) {
        this.disabled = disabled;
    }
    
    public String getName() {
        return this.name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getTheme() {
        return this.theme;
    }
    
    public void setTheme(String theme) {
        this.theme = theme;
    }
}
