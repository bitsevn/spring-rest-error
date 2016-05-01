package com.cubestacklabs.resterror.classic;

import java.io.Serializable;

public class ErrorTemplate implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private String exceptions;
	
	private ErrorModel error;
	
	public ErrorTemplate() {}
	
	public ErrorTemplate(String exceptions, ErrorModel error) {
		this.exceptions = exceptions;
		this.error = error;
	}

	public String getExceptions() {
		return exceptions;
	}

	public void setExceptions(String exceptions) {
		this.exceptions = exceptions;
	}

	public ErrorModel getError() {
		return error;
	}

	public void setError(ErrorModel error) {
		this.error = error;
	}

	public boolean valid() {
		if(exceptions == null || error == null || !error.valid()) return false;
		return true;
	}

}
