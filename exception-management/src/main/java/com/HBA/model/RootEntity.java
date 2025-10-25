package com.HBA.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RootEntity<T> {

	private boolean result; //Servisin başarılı olup olmadığını tutan değişken 
	
	private String errorMessage; //Servisin neden başarısız olduğunun hata mesajını tutan değişken, başarılıysa NULL atanır. 
	
	private T data; //Servisin başarılı olduğu senaryodaki datası, başarısızsa NULL atanır.
	
	public static <T> RootEntity<T> ok(T data){
		RootEntity<T> rootEntity = new RootEntity<>();
		
		rootEntity.setData(data);
		rootEntity.setErrorMessage(null);
		rootEntity.setResult(true);
		return rootEntity;
	}
	
	public static <T> RootEntity<T> error(String errorMessage){
		RootEntity<T> rootEntity = new RootEntity<>();
		
		rootEntity.setData(null);
		rootEntity.setErrorMessage(errorMessage);
		rootEntity.setResult(false);
		return rootEntity;
	}
}
