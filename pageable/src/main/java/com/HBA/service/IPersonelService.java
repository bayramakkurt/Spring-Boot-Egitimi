package com.HBA.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.HBA.dto.DTOPersonel;
import com.HBA.model.Personel;

public interface IPersonelService {

	Page<Personel> findAllPageable(Pageable pageable);

	List<DTOPersonel> toDTOList(List<Personel> personelList);
}
