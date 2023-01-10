package com.cinshop.common.exception;

public class OrderException extends Exception {

	private String msg;

	public OrderException(String msg) {
		super();
		this.msg = msg;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

}
