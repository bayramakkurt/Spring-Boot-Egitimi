package com.HBA.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.HBA.model.Employee;

@Configuration
public class AppConfig {

	@Bean
	public List<Employee> employeeList(){
		List<Employee> employeeList = new ArrayList<>();
		
		employeeList.add(new Employee("1", "Hacı Bayram", "AKKURT"));
		employeeList.add(new Employee("2", "Zeynal Abidin", "ÜNLÜ"));
		employeeList.add(new Employee("3", "Yakup", "ÖZDİL"));
		employeeList.add(new Employee("4", "Muhammed Mustafa", "ÜNLÜ"));
		employeeList.add(new Employee("5", "Taha Eren", "ÇELEBİ"));
		
		return employeeList;
	}
}
