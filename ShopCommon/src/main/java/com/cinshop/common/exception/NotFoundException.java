package com.cinshop.common.exception;

public class NotFoundException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String msg;

	public NotFoundException(String msg) {
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
