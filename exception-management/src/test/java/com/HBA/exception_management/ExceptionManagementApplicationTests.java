package com.HBA.exception_management;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.HBA.dto.DTOEmployee;
import com.HBA.service.IEmployeeService;
import com.HBA.starter.ExceptionManagementStarter;

@SpringBootTest(classes = {ExceptionManagementStarter.class}) //Sınıfın test sınıfı olduğunu belirtir. Parametre ise uygulamayı yürütecek class bulamazsa diye eklenir.
class ExceptionManagementApplicationTests {

	//Test metodları geriye tür dönmez o yüzden void yazılır hepsi.
	
	@Autowired
	private IEmployeeService employeeService; //Test edilirken controller bölümünden alınmaz servis bölümünden alınır.
	
	@BeforeEach //Test metodlarından önce çalışır.
	public void beforeEach() {
		System.out.println("BeforeEach çalıştı...");
	}
	
	@Test
	public void testFindEmployeeByID() {
		DTOEmployee employee = employeeService.findEmployeeByID(1L); //Parametre olarak LONG tipinde beklediği için sayının yanına L yazıldı.
		if (employee!=null) {
			System.out.println("İsim : " + employee.getName());
		}
		assertNotNull(employee); //Sonuç eşitmi değilmi vs. şartlarını kontrol eder.
	}
	
	@AfterEach  //Test metodlarından sonra çalışır.
	public void afterEach() {
		System.out.println("AfterEach çalıştı...");
	}

}
