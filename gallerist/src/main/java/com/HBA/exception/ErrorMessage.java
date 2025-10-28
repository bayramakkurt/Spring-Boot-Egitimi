package com.HBA.exception;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ErrorMessage {

	private String ofStatic;
	
	private MessageType messageType;
	
	public String prepareErrorMessage() {
		StringBuilder builder = new StringBuilder();
		builder.append(messageType.getMessage());
		if (this.ofStatic!=null) {
			builder.append(" : " + ofStatic);
		}
		return builder.toString();
	}
}
