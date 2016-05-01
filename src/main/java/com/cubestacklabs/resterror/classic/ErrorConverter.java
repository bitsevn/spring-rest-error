package com.cubestacklabs.resterror.classic;

import java.util.LinkedHashMap;
import java.util.Map;

public class ErrorConverter {
	
	private static final String STATUS_KEY = "status";
    private static final String CODE_KEY = "code";
    private static final String MESSAGE_KEY = "message";
    private static final String DEVELOPER_MESSAGE_KEY = "developerMessage";
    private static final String MORE_INFO_URL_KEY = "moreInfoUrl";

	public Map<String, Object> convert(ErrorModel model) {
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		map.put(STATUS_KEY, model.getStatus());
		if(model.getCode() != null) {
			map.put(CODE_KEY, model.getCode());
		}
		if(model.getDeveloperMessage() != null) {
			map.put(DEVELOPER_MESSAGE_KEY, model.getDeveloperMessage());
		}
		if(model.getMessage() != null) {
			map.put(MESSAGE_KEY, model.getMessage());
		}
		if(model.getMoreInfoUrl() != null) {
			map.put(MORE_INFO_URL_KEY, model.getMoreInfoUrl());
		}
		return map;
	}
}
