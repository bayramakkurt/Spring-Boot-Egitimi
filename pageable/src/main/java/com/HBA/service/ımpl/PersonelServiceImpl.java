package com.HBA.service.ımpl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.HBA.dto.DTODepartment;
import com.HBA.dto.DTOPersonel;
import com.HBA.model.Personel;
import com.HBA.repository.PersonelRepository;
import com.HBA.service.IPersonelService;

@Service
public class PersonelServiceImpl implements IPersonelService {

	@Autowired
	private PersonelRepository personelRepository;
	
	@Override
	public Page<Personel> findAllPageable(Pageable pageable) {
		return personelRepository.findAllPageable(pageable);
	}

	@Override
	public List<DTOPersonel> toDTOList(List<Personel> personelList) {
		List<DTOPersonel> dtoList = new ArrayList<>();
		
		for (Personel personel : personelList) {
			DTOPersonel dtoPersonel = new DTOPersonel();
			DTODepartment dtoDepartment = new DTODepartment();
			BeanUtils.copyProperties(personel, dtoPersonel);
			BeanUtils.copyProperties(personel.getDepartment(), dtoDepartment);
			
			dtoPersonel.setDepartment(dtoDepartment);
			
			dtoList.add(dtoPersonel);
		}
		
		return dtoList;
	}

}
