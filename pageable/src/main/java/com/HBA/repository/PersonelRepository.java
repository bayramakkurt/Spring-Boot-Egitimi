package com.HBA.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.HBA.model.Personel;

@Repository
public interface PersonelRepository extends JpaRepository<Personel, Long> {

	//Veritabanından bütün personelleri getirir. Paremetre olarak aldığı Pageable dğeişkenine göre sayfalar ve Page<Entity> türüne göre döner.
	@Query(value = "from Personel")
	Page<Personel> findAllPageable(Pageable pageable); // Data domain altından gelen pageable veri türü seçilmeli.
}
