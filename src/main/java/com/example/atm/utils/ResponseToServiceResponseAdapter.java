package com.example.atm.utils;

import java.util.Map;

import com.example.atm.component.ServiceResponse;
import com.example.atm.component.ResponseStatus;

public class ResponseToServiceResponseAdapter {

	public static <T> ServiceResponse<T> toServiceResponse(T result) {
		ServiceResponse<T> serviceResponse = new ServiceResponse<>();
		serviceResponse.setStatus(ResponseStatus.SUCCESS);
		serviceResponse.setResponse(result);
		return serviceResponse;
	}

	public static <T> ServiceResponse<T> toServiceResponse(T result, Map<String, String> info) {
		ServiceResponse<T> serviceResponse = new ServiceResponse<>();
		serviceResponse.setStatus(ResponseStatus.SUCCESS);
		serviceResponse.setResponse(result);
		serviceResponse.setInfo(info);
		return serviceResponse;
	}

	public static <T> ServiceResponse<T> toServiceResponse(Exception e) {
		ServiceResponse<T> serviceResponse = new ServiceResponse<>();
		serviceResponse.setStatus(ResponseStatus.FAILURE);
		serviceResponse.setException(e);
		return serviceResponse;
	}

	public static <T> ServiceResponse<T> toServiceResponse(Exception e, ResponseStatus status) {
		ServiceResponse<T> serviceResponse = new ServiceResponse<>();
		serviceResponse.setStatus(status);
		serviceResponse.setException(e);
		return serviceResponse;
	}

}
