package com.pk.exception;

public class TokenException extends RuntimeException{
	private static final long serialVersionUID = 14636356485853453L;
	
	public TokenException(String msg) {
		super();
		this.msg = msg;
	}
	
	private String msg;

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
	
	
}
