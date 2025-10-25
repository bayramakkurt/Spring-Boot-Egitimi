package com.HBA.exception;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice //Artık altındaki sınıf ExceptionHandler olduğu belirtildi
public class GlobalExceptionHandler {
	
	private List<String> addMapValue(List<String> list, String newValue){ //HashMap eleman eklemek için kullandık
		list.add(newValue);
		return list;
	}

	@ExceptionHandler(value = MethodArgumentNotValidException.class) //Fonksiyonun hata yakalama için olacğaını belirtir. Parametresi ise hata türünü belirtir.
	public ResponseEntity<APIError> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) { //Burdada hata türü parametre olarak verilir.
		Map<String, List<String>> errorsMap = new HashMap<>(); 
		
		for (ObjectError objError : ex.getBindingResult().getAllErrors()) { //Bütün hataları dönen List içinde gezer
			String fieldName = ((FieldError)objError).getField(); //Hata veren Değişken adını almak için kullandık.
			if (errorsMap.containsKey(fieldName)) { //Eğer daha önce aynı değişkene hata mesajı varsa onun üzerine eklemek için bu if kullanılır.
				
				//Burada o değişkene ait hata mesajı varsa o hata mesajları get ile getirilir ardından yeni gelen hata mesajı listeye dahil edilir.
				errorsMap.put(fieldName, addMapValue(errorsMap.get(fieldName), objError.getDefaultMessage()));
				
			}else { //Eğerki daha önce hiç hata mesajı almadıysa değişken yeni bir hata mesajı listesi oluşturmak için kullanılır.
				
				//Burada o değişkene ait hata mesajı yoksa yeni bir arraylist oluşturulur ve hata mesajı listeye dahil edilir.
				errorsMap.put(fieldName, addMapValue(new ArrayList<>(), objError.getDefaultMessage()));
				
			}
		}
		
		return ResponseEntity.badRequest().body(createAPIError(errorsMap));
	}
	
	private <T> APIError<T> createAPIError(T errors) { //Buradaki T Generic olmasını sağlar yani hangi veri türünde veri gelirse gelsin hata mesajı oluşturulur. 
		APIError<T> apiError = new APIError<T>();
		apiError.setId(UUID.randomUUID().toString());
		apiError.setErrorTime(new Date());
		apiError.setErrors(errors);
		
		return apiError;
	}
}
