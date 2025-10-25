package com.HBA.handler;

import lombok.Data;

@Data
public class APIError<E> {

	private Integer statusCode;
	
	private Exception<E> exception;
}
