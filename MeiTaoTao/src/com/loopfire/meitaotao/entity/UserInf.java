package com.loopfire.meitaotao.entity;


import java.io.Serializable;

/**
 * 登录的用户信息
 *
 */
public class UserInf implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1839391886503818909L;
	////编号
	String uid ="";
	///用户名
	String username ="";
	///密码
	String password="";
	
	///邮箱
	String email="";
	//手机号码
	String mobile="";
	//头像
	String avatar="";
	//公司
	String company = "";
	String address = "";
	
	//好友模块
	//距离
	String distance = "";
	//职位
	String position = "";
	//是否在线
	String isonline = "";
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	String fullname = "";
	//时间(fan加)
	String status="";
	String privatestr=""; 
	public String getPrivatestr() {
		return privatestr;
	}
	public void setPrivatestr(String privatestr) {
		this.privatestr = privatestr;
	}
	String city="";
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	String dateline="";
	private String realName;
	public String getRealName() {
		return realName;
	}
	public void setRealName(String realName) {
		this.realName = realName;
	}
	public String getDateline() {
		return dateline;
	}
	public void setDateline(String dateline) {
		this.dateline = dateline;
	}
	public String getFullname() {
		return fullname;
	}
	public void setFullname(String fullname) {
		this.fullname = fullname;
	}
	public String getIsonline() {
		return isonline;
	}
	public void setIsonline(String isonline) {
		this.isonline = isonline;
	}
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getDistance() {
		return distance;
	}
	public void setDistance(String distance) {
		this.distance = distance;
	}
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
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
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getAvatar() {
		return avatar;
	}
	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}
	
}
