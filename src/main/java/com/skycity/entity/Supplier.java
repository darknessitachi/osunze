package com.skycity.entity;

import org.apache.ibatis.type.Alias;

/**
 * 供应商信息管理
 * 
 * @author YingBo
 *
 */
@Alias("Supplier")
public class Supplier {
	private String id;
	private String name;
	private String contacts; // 联系人
	private String phone; // 供应商联系电话
	private String email; // 供应商联系邮箱
	private String qq; // 供应商联系QQ
	private String wx; // 供应商联系weixin
	private String address; // 供应商地址
	private String description; // 供应商描述信息
	private String webSite;		//供应商网址
	
	private String qrText;		//查询字段
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getContacts() {
		return contacts;
	}

	public void setContacts(String contacts) {
		this.contacts = contacts;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getQq() {
		return qq;
	}

	public void setQq(String qq) {
		this.qq = qq;
	}

	public String getWx() {
		return wx;
	}

	public void setWx(String wx) {
		this.wx = wx;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getQrText() {
		return qrText;
	}

	public void setQrText(String qrText) {
		this.qrText = qrText;
	}

	public String getWebSite() {
		return webSite;
	}

	public void setWebSite(String webSite) {
		this.webSite = webSite;
	}
	
}
