package com.HBA.handler;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import com.HBA.exception.BaseException;

@ControllerAdvice //Hata mesajı yakalaması için gerekli anatasyon
public class GlobalExceptionHandler {

	@ExceptionHandler(value = {BaseException.class})
	public ResponseEntity<APIError> handleBaseException(BaseException exception, WebRequest request) {

		return ResponseEntity.badRequest().body(createAPIError(exception.getMessage(), request));
	}
	
	private String getHostName() {
		try {
			return InetAddress.getLocalHost().getHostName();
		} catch (UnknownHostException e) {
			System.out.println("Hata Oluştu: " + e.getMessage());
		}
		return null;
	}
	
	public <E> APIError<E> createAPIError(E message,WebRequest request){
		APIError<E> apiError = new APIError<>();
		apiError.setStatusCode(HttpStatus.BAD_REQUEST.value());
		
		Exception<E> exception = new Exception<>();
		exception.setCreateTime(new Date());
		exception.setHostName(getHostName());
		exception.setPath(request.getDescription(false).substring(4));
		exception.setMessage(message);
		
		apiError.setException(exception);
		
		return apiError;
		
	}
}
