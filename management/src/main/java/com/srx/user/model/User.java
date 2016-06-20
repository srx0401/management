package com.srx.user.model;


import javax.persistence.Column;
import javax.persistence.Entity;

import com.srx.model.base.BaseModel;
@Entity(name="SRX_USER")
public class User extends BaseModel{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 904962748831745775L;
	/**
	 * 用户名
	 */
	@Column(nullable=false)
	private String username;
	/**
	 * 密码
	 */
	@Column(nullable=false)
	private String password;
	/**
	 * 名称
	 */
	private String name;
	/**
	 * 年龄
	 */
	private int age;
	/**
	 * 性别
	 */
	private String gender;
	/**
	 * 籍贯
	 */
	private String nativePlace;
	/**
	 * 地址
	 */
	private String address;
	/**
	 * 手机号码
	 */
	@Column(nullable=false)
	private String phone;
	/**
	 * QQ号码
	 */
	private String qq;
	/**
	 * 微信号码
	 */
	private String wechat;
	/**
	 * 电子邮箱
	 */
	private String mail;
	
	private boolean isRoot = false;
	
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
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getQq() {
		return qq;
	}
	public void setQq(String qq) {
		this.qq = qq;
	}
	public String getWechat() {
		return wechat;
	}
	public void setWechat(String wechat) {
		this.wechat = wechat;
	}
	public boolean isRoot() {
		return isRoot;
	}
	public void setRoot(boolean isRoot) {
		this.isRoot = isRoot;
	}
	public String getNativePlace() {
		return nativePlace;
	}
	public void setNativePlace(String nativePlace) {
		this.nativePlace = nativePlace;
	}
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
	
}
