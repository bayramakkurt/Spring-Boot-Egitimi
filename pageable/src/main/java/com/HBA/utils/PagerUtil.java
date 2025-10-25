package com.HBA.utils;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;

import lombok.experimental.UtilityClass;

@UtilityClass //Bu anatasyonla işaretlenen classlardan nesne türetilemez ve içindeki metodlar static gibi direkt kullanılabilir.
public class PagerUtil {

	public boolean isNullOrEmpty(String value) {
		return value==null || value.trim().length()==0; //Girilen parametre null veya boşluklar silindikten sonraki uzunluğu 0 ise True dön.
	}
	
	public PageRequest toPageable(RestPageableRequest pageableRequest) {
		if (!isNullOrEmpty(pageableRequest.getColumnName())) { //Kullanıcı columnName parametresi ile sorgu göndermişse filtreleme yapmak istiyor.
			Sort sortBy = pageableRequest.isAsc() ? Sort.by(Direction.ASC, pageableRequest.getColumnName()) //Parametre olarak ASC girildiyse ona göre sırala değilse DESC olarak sırala.
					: Sort.by(Direction.DESC, pageableRequest.getColumnName());
			return PageRequest.of( pageableRequest.getPageNumber(), pageableRequest.getPageSize(),sortBy);
		}
		return PageRequest.of( pageableRequest.getPageNumber(), pageableRequest.getPageSize()); //Kullanıcı columnName parametresi boş geçerse filtreleme olamadan page verileri gelir.
	}
	
	public <T> RestPageableEntity<T> toPageableResponse(Page<?> page, List<T> contet) {
		RestPageableEntity<T> pageableEntity = new RestPageableEntity<T>();
		pageableEntity.setContent(contet);
		pageableEntity.setPageNumber(page.getPageable().getPageNumber());
		pageableEntity.setPageSize(page.getPageable().getPageSize());
		pageableEntity.setTotalElements(page.getTotalElements());
		
		return pageableEntity; 
	}
}
