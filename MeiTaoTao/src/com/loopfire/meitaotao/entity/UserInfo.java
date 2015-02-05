package com.loopfire.meitaotao.entity;

import java.io.Serializable;

public class UserInfo implements Serializable {
	private String userId;
	private String userName;
	private String userAccount;
	private String userNickname;
	private String userAddress;
	private String userPortrait;

	public UserInfo(String userId, String userName, String userAccount,
			String userNickname, String userAddress, String userPortrait) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.userAccount = userAccount;
		this.userNickname = userNickname;
		this.userAddress = userAddress;
		this.userPortrait = userPortrait;
	}

	public UserInfo() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserAccount() {
		return userAccount;
	}

	public void setUserAccount(String userAccount) {
		this.userAccount = userAccount;
	}

	public String getUserNickname() {
		return userNickname;
	}

	public void setUserNickname(String userNickname) {
		this.userNickname = userNickname;
	}

	public String getUserAddress() {
		return userAddress;
	}

	public void setUserAddress(String userAddress) {
		this.userAddress = userAddress;
	}

	public String getUserPortrait() {
		return userPortrait;
	}

	public void setUserPortrait(String userPortrait) {
		this.userPortrait = userPortrait;
	}

}
