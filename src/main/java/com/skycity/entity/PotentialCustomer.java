package com.skycity.entity;

/**
 * 潜在客户信息
 * 
 * @author daiyingbo
 *
 */
public class PotentialCustomer {
	private int id;
	private String name;		//姓名
	private String sex;			//性别
	private String mobile;		//电话
	private String email;		
	private String qq;		
	private String webChat;	
	private String job;			//职业
	private String address;		//客户地址
	private String description;		//客户描述
	private String comeFrom;
	private String visitDate;		//来电，来访时间
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getComeFrom() {
		return comeFrom;
	}

	public void setComeFrom(String comeFrom) {
		this.comeFrom = comeFrom;
	}

	public String getVisitDate() {
		return visitDate;
	}

	public void setVisitDate(String visitDate) {
		this.visitDate = visitDate;
	}

}
