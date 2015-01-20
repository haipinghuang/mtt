package com.loopfire.meitaotao.entity;

public class MyMessage {
	private String userName;
	private String msg_body;
	private String receive_time;

	public MyMessage() {
		super();
	}

	public MyMessage(String userName, String msg_body, String receive_time) {
		super();
		this.userName = userName;
		this.msg_body = msg_body;
		this.receive_time = receive_time;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getMsg_body() {
		return msg_body;
	}

	public void setMsg_body(String msg_body) {
		this.msg_body = msg_body;
	}

	public String getReceive_time() {
		return receive_time;
	}

	public void setReceive_time(String receive_time) {
		this.receive_time = receive_time;
	}

}
