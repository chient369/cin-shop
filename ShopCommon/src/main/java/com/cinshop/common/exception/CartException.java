package com.cinshop.common.exception;

public class CartException extends Exception {

	private String msg;

	public CartException(String msg) {
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
