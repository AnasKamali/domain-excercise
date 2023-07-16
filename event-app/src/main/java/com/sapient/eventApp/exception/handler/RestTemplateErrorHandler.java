package com.sapient.eventApp.exception.handler;

import java.io.IOException;

import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.client.ResponseErrorHandler;

import com.sapient.eventApp.exception.EmployeeNotExistException;

@Component
public class RestTemplateErrorHandler implements ResponseErrorHandler {

	@Override
	public boolean hasError(ClientHttpResponse httpResponse) throws IOException {
		return httpResponse.getStatusCode().isError();
	}

	@Override
	public void handleError(ClientHttpResponse response) throws IOException {
		throw new EmployeeNotExistException("employee not found exception");

	}

}
