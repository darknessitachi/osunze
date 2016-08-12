package com.skycity.framework.tag;

import java.io.IOException;

import javax.servlet.jsp.tagext.BodyTagSupport;

import com.skycity.framework.utility.StringUtil;

public class CheckboxTag extends BodyTagSupport {
	
	private static final long serialVersionUID = 1L;
	protected String code;
	protected String id;
	protected String method;
	protected String name;
	protected boolean tableLayout;
	protected String tableWidth;
	protected int column;
	protected String onChange;
	protected String onClick;
	protected String value;
	protected String disabled;
	protected String defaultCheck;
	protected String type;
	protected String theme;
	protected String options;

	public String getPrefix() {
		return "z";
	}

	public String getTagName() {
		return "checkbox";
	}

	public void init() {
		this.type = "checkbox";
	}

	public int doStartTag() {
		try {
			this.pageContext.getOut().print(getHtml());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return 0;
	}

	public String getHtml() {
		if (StringUtil.isEmpty(id)) {
			this.id = "_ZVING_NOID";
		}
		if (StringUtil.isEmpty(name)) {
			this.name = this.id;
		}
		String html = "";
		if(StringUtil.isNotEmpty(code)){
			
			html = "<input type=\"" + this.type +"\" id=\"" + this.name +"\"/>";
		}
		return html;
	}

	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getMethod() {
		return this.method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isTableLayout() {
		return this.tableLayout;
	}

	public void setTableLayout(boolean tableLayout) {
		this.tableLayout = tableLayout;
	}

	public String getTableWidth() {
		return this.tableWidth;
	}

	public void setTableWidth(String tableWidth) {
		this.tableWidth = tableWidth;
	}

	public int getColumn() {
		return this.column;
	}

	public void setColumn(int column) {
		this.column = column;
	}

	public String getOnChange() {
		return this.onChange;
	}

	public void setOnChange(String onChange) {
		this.onChange = onChange;
	}

	public String getOnClick() {
		return this.onClick;
	}

	public void setOnClick(String onClick) {
		this.onClick = onClick;
	}

	public String getValue() {
		return this.value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getDisabled() {
		return this.disabled;
	}

	public void setDisabled(String disabled) {
		this.disabled = disabled;
	}

	public String getDefaultCheck() {
		return this.defaultCheck;
	}

	public void setDefaultCheck(String defaultCheck) {
		this.defaultCheck = defaultCheck;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getTheme() {
		return this.theme;
	}

	public void setTheme(String theme) {
		this.theme = theme;
	}

	public String getOptions() {
		return this.options;
	}

	public void setOptions(String options) {
		this.options = options;
	}

	public static long getSerialversionuid() {
		return 1L;
	}

	public String getOnclick() {
		return this.onClick;
	}

	public void setOnclick(String onClick) {
		this.onClick = onClick;
	}
}