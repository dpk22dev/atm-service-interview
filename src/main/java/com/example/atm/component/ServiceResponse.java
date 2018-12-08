package com.example.atm.component;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

@Component
public class ServiceResponse<T> {	
	private ResponseStatus		status;
	private T					response;
	private Exception			exception;
	private Map<String, String>	info;

	
	public Map<String, String> getInfo() {
		if (info == null)
			info = new HashMap<>();
		return info;
	}

	public void setInfo(Map<String, String> info) {
		this.info = info;
	}

	public ResponseStatus getStatus() {
		return status;
	}

	public void setStatus(ResponseStatus status) {
		this.status = status;
	}

	public T getResponse() {
		return response;
	}

	public void setResponse(T response) {
		this.response = response;
	}

	public Exception getException() {
		return exception;
	}

	public void setException(Exception exception) {
		this.exception = exception;
	}
}
