package com.HBA.services.impl;

import java.beans.beancontext.BeanContext;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.HBA.dto.DTOHome;
import com.HBA.dto.DTORoom;
import com.HBA.entities.Home;
import com.HBA.entities.Room;
import com.HBA.repository.HomeRepository;
import com.HBA.services.IHomeService;

@Service
public class HomeServiceImpl implements IHomeService {

	@Autowired
	private HomeRepository homeRepository;

	@Override
	public DTOHome findHomeByID(Long id) {
		DTOHome dtoHome = new DTOHome();
		Optional<Home> optional = homeRepository.findById(id);
		if (optional.isEmpty()) {
			return null;
		}
		
		Home dbHome = optional.get(); //Veritabanında bulunan EVİ getirir.
		List<Room> dbRooms = optional.get().getRoom(); //Veritabanında bulunan EVE ait ODALARI getirir.
		
		BeanUtils.copyProperties(dbHome, dtoHome); //dbHome içindeki verileri DTO olarak döneceğimiz için DTOHOME kopyaladık.
		
		if (dbRooms != null && !dbRooms.isEmpty()) { //Eğer eve ait oda varsa şarta gir.
			for (Room room : dbRooms) { //Bütün odaları for ile gez
				DTORoom dtoRoom = new DTORoom(); //Her odayı DTO çevir
				
				BeanUtils.copyProperties(room, dtoRoom); //DTO döneceğimiz için her odayı DTO kopyala
				dtoHome.getRooms().add(dtoRoom); //Burada ise her DTOROOM EV üzerinden oda listesi çekilerek ona eklenir.
				
			}
		}
		return dtoHome;
	}
	
}
