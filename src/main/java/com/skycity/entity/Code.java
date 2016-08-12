package com.skycity.entity;

import org.apache.ibatis.type.Alias;

@Alias("Code")
public class Code {
	private String codeName;
	private String codeType;
	private String status;
	private String codeMemo;
	private String parentCode;
	private String parentCodeName;
	private String system;

	private String qrCode; // 查询的字段名
	private String operator;	//操作
	public String getSystem() {
		return system;
	}

	public void setSystem(String system) {
		this.system = system;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getCodeName() {
		return codeName;
	}

	public void setCodeName(String codeName) {
		this.codeName = codeName;
	}

	public String getCodeType() {
		return codeType;
	}

	public void setCodeType(String codeType) {
		this.codeType = codeType;
	}

	public String getCodeMemo() {
		return codeMemo;
	}

	public void setCodeMemo(String codeMemo) {
		this.codeMemo = codeMemo;
	}

	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	public String getParentCode() {
		return parentCode;
	}

	public void setParentCode(String parentCode) {
		this.parentCode = parentCode;
	}

	public String getParentCodeName() {
		return parentCodeName;
	}

	public String getQrCode() {
		return qrCode;
	}

	public void setQrCode(String qrCode) {
		this.qrCode = qrCode;
	}

	public void setParentCodeName(String parentCodeName) {
		this.parentCodeName = parentCodeName;
	}
}