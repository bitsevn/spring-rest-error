package com.cubestacklabs.resterror.classic;

import java.io.Serializable;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.http.HttpStatus;
import org.springframework.util.ObjectUtils;

public class ErrorModel implements Serializable {

	private static final long serialVersionUID = 1L;

	private String status;
	private String code;
	private String message;
	private String developerMessage;
	private String moreInfoUrl;
	private String stackTrace;
	private Throwable throwable;
	private String timestamp;
	private String path;
	
	public ErrorModel() {}

	public ErrorModel(String status, String code, String message, String developerMessage, String moreInfoUrl,
			Throwable throwable) {
		if (status == null) {
			throw new NullPointerException("HttpStatus argument cannot be null.");
		}
		this.status = status;
		this.code = code;
		this.message = message;
		this.developerMessage = developerMessage;
		this.moreInfoUrl = moreInfoUrl;
		this.stackTrace = (throwable != null) ? ExceptionUtils.getStackTrace(throwable) : null;
		this.throwable = throwable;
	}

	public String getStatus() {
		return status;
	}

	public String getCode() {
		return code;
	}

	public String getMessage() {
		return message;
	}

	public String getDeveloperMessage() {
		return developerMessage;
	}

	public String getMoreInfoUrl() {
		return moreInfoUrl;
	}

	public Throwable getThrowable() {
		return throwable;
	}

	public String getStackTrace() {
		return stackTrace;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public void setDeveloperMessage(String developerMessage) {
		this.developerMessage = developerMessage;
	}

	public void setMoreInfoUrl(String moreInfoUrl) {
		this.moreInfoUrl = moreInfoUrl;
	}

	public void setStackTrace(String stackTrace) {
		if(this.stackTrace != null) return;
		this.stackTrace = stackTrace;
	}

	public void setThrowable(Throwable throwable) {
		if(throwable != null) {
			this.stackTrace = ExceptionUtils.getStackTrace(throwable);
		}
		this.throwable = throwable;
	}

	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o instanceof ErrorModel) {
			ErrorModel re = (ErrorModel) o;
			return ObjectUtils.nullSafeEquals(getStatus(), re.getStatus())
					&& ObjectUtils.nullSafeEquals(getCode(), re.getCode())
					&& ObjectUtils.nullSafeEquals(getMessage(), re.getMessage())
					&& ObjectUtils.nullSafeEquals(getDeveloperMessage(), re.getDeveloperMessage())
					&& ObjectUtils.nullSafeEquals(getMoreInfoUrl(), re.getMoreInfoUrl())
					&& ObjectUtils.nullSafeEquals(getThrowable(), re.getThrowable());
		}

		return false;
	}

	@Override
	public int hashCode() {
		return ObjectUtils.nullSafeHashCode(new Object[] { getStatus(), getCode(), getMessage(), getDeveloperMessage(),
				getMoreInfoUrl(), getThrowable() });
	}

	public String toString() {
		HttpStatus httpStatus = HttpStatus.valueOf(getStatus());
		return new ToStringBuilder(this).append("status", status).append("reason", httpStatus.getReasonPhrase())
				.append("code", code).append("message", message).append("developerMessage", developerMessage)
				.append("moreInfoUrl", moreInfoUrl).toString();
	}

	public boolean valid() {
		if (status == null || code == null)
			return false;
		return true;
	}
}