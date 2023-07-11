package com.sapient.eventApp.exception.handler;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import com.sapient.eventApp.exception.EventDoesNotExistsException;


@ControllerAdvice
public class ExceptionHanler {

	@ExceptionHandler(EventDoesNotExistsException.class)
	public ResponseEntity<Object> handleCityNotFoundException(EventDoesNotExistsException ex, WebRequest request) {

		Map<String, Object> body = new LinkedHashMap<>();
		body.put("timestamp", LocalDateTime.now());
		body.put("message", ex.getLocalizedMessage());

		return new ResponseEntity<>(body, HttpStatus.NO_CONTENT);
	}

}
