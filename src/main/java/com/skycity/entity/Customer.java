package com.skycity.entity;

import org.apache.ibatis.type.Alias;

@Alias("Customer")
public class Customer {
	private String id;
	private String name; 			// 客户姓名
	private String sex; 			// 性别
	private String mobile; 			// 电话
	private String email; 			// 邮箱
	private String qq; 				// ociq号码
	private String webChat; 		// 微信账号
	private String job; 			// 职业
	private String address;			// 住址
	private String comefrom; 		// 来源
	private String description; 	// 描述

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

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
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

	public String getWebChat() {
		return webChat;
	}

	public void setWebChat(String webChat) {
		this.webChat = webChat;
	}

	public String getJob() {
		return job;
	}

	public void setJob(String job) {
		this.job = job;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getComefrom() {
		return comefrom;
	}

	public void setComefrom(String comefrom) {
		this.comefrom = comefrom;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
