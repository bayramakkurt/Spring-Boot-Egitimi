package com.HBA.controller.impl;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RestController;

import com.HBA.utils.PagerUtil;
import com.HBA.utils.RestPageableEntity;
import com.HBA.utils.RestPageableRequest;
import com.HBA.utils.RestRootEntity;

@RestController
public class RestBaseController {

	
	public Pageable toPageable(RestPageableRequest pageableRequest) {
		return PagerUtil.toPageable(pageableRequest);
	}
	
	public <T> RestPageableEntity<T> toPageableResponse(Page<?> page, List<T> content) {
		return PagerUtil.toPageableResponse(page, content);
	}
	
	public <T> RestRootEntity<T> ok(T payload){
		return RestRootEntity.ok(payload);
	}
}
