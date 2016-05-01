package com.cubestacklabs.resterror.classic;

import java.io.Serializable;

public class ErrorMapping implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	ErrorTemplate[] errorMappings;

	public ErrorTemplate[] getErrorMappings() {
		return errorMappings;
	}

	public void setErrorMappings(ErrorTemplate[] errorMappings) {
		this.errorMappings = errorMappings;
	}
	
}
