package com.HBA.controller.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.HBA.controller.IRestPersonelController;
import com.HBA.dto.DTOPersonel;
import com.HBA.model.Personel;
import com.HBA.service.IPersonelService;
import com.HBA.utils.RestPageableEntity;
import com.HBA.utils.RestPageableRequest;
import com.HBA.utils.RestRootEntity;

@RestController
@RequestMapping(path = "/rest/api/personel")
public class RestPersonelController extends RestBaseController implements IRestPersonelController{

	@Autowired
	private IPersonelService personelService;
	
	@GetMapping(path = "/list/pageable")
	@Override
	public RestRootEntity<RestPageableEntity<DTOPersonel>> findAllPageable(RestPageableRequest pageableRequest) {
		Page<Personel> page = personelService.findAllPageable(toPageable(pageableRequest));
		RestPageableEntity<DTOPersonel> pageableResponse = toPageableResponse(page, personelService.toDTOList(page.getContent()));
		return ok(pageableResponse);
	}


	
	
	
	
//	@GetMapping(path = "/list/pageable")
//	@Override
//	public Page<Personel> findAllPageable(RestPageableRequest pageableRequest) {
//		Pageable pageable = toPageable(pageableRequest);
//		return personelService.findAllPageable(pageable);
//	}

//	//@ModelAttribute kullanarak parametleri ve sorguları verdik.
//	@GetMapping(path = "/list/pageable")
//	@Override
//	public Page<Personel> findAllPageable(@ModelAttribute RestPageableRequest pageableRequest) {
//		Pageable pageable = PageRequest.of(pageableRequest.getPageNumber(), pageableRequest.getPageSize(),
//				Sort.by(Direction.DESC, "id"));
//		return personelService.findAllPageable(pageable);
//	}
	
//	@GetMapping(path = "/list/pageable")
//	@Override
//	public Page<Personel> findAllPageable(@RequestParam(value = "pageNumber") int pageNumber,
//										  @RequestParam(value = "pageSize") int pageSize) {
//		PageRequest pageable = PageRequest.of(pageNumber, pageSize, Sort.by(Direction.DESC, "id")); //Dolaylı yoldan pageable oluşturmak için kullandık. Pageable nesnesini kapsadığı için PageRequestde verilebilir.
//		return personelService.findAllPageable(pageable);
//	}
	

}
