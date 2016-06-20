package com.srx.account.model;


import javax.persistence.Column;
import javax.persistence.Entity;

import com.srx.model.base.BaseModel;
@Entity(name="SRX_ACCOUNT")
public class Account extends BaseModel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 9119857483872076234L;
	/**
	 * 账户名称
	 */
	private String name;
	/**
	 * 账户用户名
	 */
	@Column(nullable=false)
	private String username;
	/**
	 * 账户密码
	 */
	@Column(nullable=false)
	private String password;
	/**
	 * 账户提示信息
	 */
	private String prompt;
	/**
	 * 账户登陆地址
	 */
	private String address;
	/**
	 * 所属用户编号
	 */
	private String userid;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getPrompt() {
		return prompt;
	}
	public void setPrompt(String prompt) {
		this.prompt = prompt;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	
	//	用户,类型等后续
	
}
